package alcadienoquarkus.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;


import alcadienoquarkus.model.EntityVaga;
import alcadienoquarkus.service.EstagioService;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;


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
    @Path("/alocarVaga")
    @Transactional
    @Operation(summary = "Aloca um aluno em uma vaga de estagio", description = "Este endpoint permite alocar uma vaga para um aluno em um hospital.")
    public Response alocarVagaAluno(@QueryParam("nomeAluno") String nomeAluno,
            @QueryParam("nomeLocal") String nomeLocal) {

        EntityVaga vaga = service.alocarAlunoAVaga(nomeAluno, nomeLocal);
        return Response.ok().entity(vaga).build();
    }



}
