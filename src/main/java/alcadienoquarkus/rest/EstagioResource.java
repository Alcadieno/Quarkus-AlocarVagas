package alcadienoquarkus.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import alcadienoquarkus.dto.request.CreateAlunoRequest;
import alcadienoquarkus.dto.request.CreateLocalRequest;

import alcadienoquarkus.model.EntityAluno;
import alcadienoquarkus.model.EntityLocal;
import alcadienoquarkus.model.EntityVaga;
import alcadienoquarkus.service.EstagioService;
import alcadienoquarkus.valueObject.Coordenada;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstagioResource {

    @Inject
    EstagioService service;
    
    /* Baseando no INJECT , resolvi mudar para uma classe separada
     * chamada Local Resource , uma vez que para respeitar os SOLID
     *  essa classe só poderia mudar caso a logica da Classe EstagioService mudasse
     *  
     */
   

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
    public Response listTodosLocais() {
        //Resolvi deixar aqui mesmo pois é uma busca simples 
        PanacheQuery<EntityLocal> localEntity = EntityLocal.findAll();
        return Response.ok(localEntity.list()).build();
    }

    @GET
    @Path("/listarLatitudeLongitude")
    @Operation(summary = "Lista a latitude e longitude de cada entidade Local cadastrada", description = "Este endpoint retorna uma lista de coodernadas contendo a latitude e longitude de cada local cadastrado.")
    public Response listarDistancia() {
        PanacheQuery<EntityLocal> localEntity = EntityLocal.findAll();
        List<Coordenada> coordenadas = new ArrayList<>();

        for (EntityLocal entityLocal : localEntity.list()) {
            Coordenada entidadeApenasCoordenada = new Coordenada(entityLocal.getLatitude(), entityLocal.getLongitude());
            coordenadas.add(entidadeApenasCoordenada);
        }
        return Response.ok(coordenadas).build();
    }
    


    @POST
    @Path("/alocarVaga")
    @Transactional
    @Operation(summary = "Aloca uma vaga para um aluno em um lugar cadastrado", description = "Este endpoint permite alocar uma vaga para um aluno em um hospital.")
    public Response alocarVagaAluno(@QueryParam("nomeAluno") String nomeAluno,
            @QueryParam("nomeLocal") String nomeLocal) {

        EntityVaga vaga = service.alocarAlunoAVaga(nomeAluno, nomeLocal);
        return Response.ok().entity(vaga).build();
    }

    @POST
    @Path("/cadastrarAluno")
    @Transactional
    @Operation(summary = "Cadastra um aluno no sistema", description = "Este endpoint permite cadastrar um aluno no sistema.")
    public Response createAluno(CreateAlunoRequest alunoRequest) throws JsonMappingException, JsonProcessingException  {
       
        EntityAluno entityAluno = service.criarAluno(alunoRequest);

        return Response.ok(entityAluno).build();
    }

}
