package alcadienoquarkus.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import alcadienoquarkus.dto.request.CreateLocalRequest;
import alcadienoquarkus.dto.request.response.OpenStreetMapServiceResponseSearch;
import alcadienoquarkus.factory.OpenStreetMapFactory;
import alcadienoquarkus.interfaces.OpenStreetMapService;
import alcadienoquarkus.model.EntityLocal;
import alcadienoquarkus.service.CoodernadaService;
import alcadienoquarkus.service.EstagioService;
import alcadienoquarkus.valueObject.Coordenada;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocalResource {

    @Inject
    CoodernadaService coordenadaService;

    @Inject
    EstagioService service;
    
    @GET
    @Path("/coordenadas")
    @Operation(summary = "Retorna as coordenadas de um endereço a partir de um CEP ou Logradouro", description = "Este endpoint retorna as coordenadas de um endereço fornecido.")
    public Response getcoodernadas(@QueryParam("endereco") String endereco)
            throws JsonMappingException, JsonProcessingException {

        OpenStreetMapService service = OpenStreetMapFactory.criarServico();

        String coordenadaArray = service.buscarCoordenadas(endereco);
        List<OpenStreetMapServiceResponseSearch> response = OpenStreetMapServiceResponseSearch
                .fromString(coordenadaArray);
        if (response.isEmpty()) {// Mudar para validar na propria classe
            return Response.status(Response.Status.NOT_FOUND).entity("Endereço não encontrado").build();
        }
        Coordenada coordenadaEmFloat = Coordenada.parseStringToFloat(response.get(0).getLat(),
                response.get(0).getLon());
        return Response.ok(coordenadaEmFloat).build();
    }

    @GET
    @Path("/logradouro")
    @Operation(summary = "Retorna o logradouro a partir de coodernadas de latitude e logitude ", description = "Este endpoint retorna o logradouro.")
    public Response getLogradouro(@QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude)
            throws JsonMappingException, JsonProcessingException {

        OpenStreetMapService service = OpenStreetMapFactory.criarServico();
                //isso aqui respeita o solid ? acredito que não
                //o ideal seria remover esses metodos para um classe alem dessa ... ja isso nao tem relacao com a logica de negocio
                //a logica de negocio seria alocar vagas , alunos e locais ;
        String endereco = service.buscarEndereco(latitude, longitude);
        return Response.ok(endereco).build();
    }

    @GET
    @Path("/menorDistancia")
    @Operation(summary = "Menor distancia entre duas coodernadas - A PASSADA NA QUERY NAO PODE SER IGUAL AS COODERNADAS DA TABELA LOCAL, JUMENTO", description = "Este endpoint retorna a coodernada de menor distancia entre a coordenada passada como parametro e as coordenadas de cada entidade local cadastrada.")
    public Response menorDistancia(@QueryParam("latitude") Float latitude, @QueryParam("longitude") Float longitude) {
        Coordenada coordenada = new Coordenada(latitude, longitude);
        List<Coordenada> coordenadasAComparar = coordenadaService.obterListaDeCoodernadas();
        Coordenada menorDistancia = coordenadaService.menorDistancia(coordenada, coordenadasAComparar);
        return Response.ok(menorDistancia).build();
    }

    @POST
    @Path("/cadastrarLocal")
    @Transactional
    @Operation(summary = "Cadastra um local de estagio no sistema", description = "Este endpoint permite cadastrar um hospital no sistema.")
    public Response create(CreateLocalRequest request) throws IOException {
        // Para esse caso o inject é melhor , para evitar ter que instanciar o 
        // Objeto EstagioService em todo endpoint
        EntityLocal localEntity = service.criarLocal(request);

        return Response.ok(localEntity).build();
    }

    @GET
    @Path("/listarLocais")
    @Operation(summary = "Lista todos os locais de estagio cadastrados", description = "Este endpoint retorna a lista de todos os hospitais cadastrados no sistema.")
    @RolesAllowed({"admin"})  // Apenas usuários com role "admin" podem acessar
    @SecurityRequirement(name = "BasicAuth")  // Swagger exige autenticação neste endpoint
    public Response listTodosLocais() {
        //Resolvi deixar aqui mesmo pois é uma busca simples 
        PanacheQuery<EntityLocal> localEntity = EntityLocal.findAll();
        return Response.ok(localEntity.list()).build();
    }

    @GET
    @Path("/listarLatitudeLongitude")
    @Operation(summary = "Lista a latitude e longitude de cada  local cadastrado", description = "Este endpoint retorna uma lista de coodernadas contendo a latitude e longitude de cada local cadastrado.")
    public Response listarDistancia() {
        PanacheQuery<EntityLocal> localEntity = EntityLocal.findAll();
        List<Coordenada> coordenadas = new ArrayList<>();

        for (EntityLocal entityLocal : localEntity.list()) {
            Coordenada entidadeApenasCoordenada = new Coordenada(entityLocal.getLatitude(), entityLocal.getLongitude());
            coordenadas.add(entidadeApenasCoordenada);
        }
        return Response.ok(coordenadas).build();
    }
}
