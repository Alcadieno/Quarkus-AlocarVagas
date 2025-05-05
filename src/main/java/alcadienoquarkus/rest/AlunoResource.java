package alcadienoquarkus.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import alcadienoquarkus.dto.request.CreateAlunoRequest;
import alcadienoquarkus.model.EntityAluno;
import alcadienoquarkus.service.EstagioService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {
    
    @Inject
    EstagioService service;
    
    @POST
    @Path("/cadastrarAluno")
    @Transactional
    @Operation(summary = "Cadastra um aluno no sistema", description = "Este endpoint permite cadastrar um aluno no sistema.")
    public Response createAluno(CreateAlunoRequest alunoRequest) throws JsonMappingException, JsonProcessingException  {
       
        EntityAluno entityAluno = service.criarAluno(alunoRequest);
        
        return Response.ok(entityAluno).build();
    }

    @GET
    @Path("/listarAlunos")
    @Operation(summary = "Lista todos os alunos cadastrados", description = "Este endpoint retorna a lista de todos os alunos cadastrados no sistema.")
    public Response listarAlunos() {
        PanacheQuery<EntityAluno> alunoEntity = EntityAluno.findAll();
        return Response.ok(alunoEntity.list()).build();
    }

    @DELETE
    @Path("/deletarAluno")
    @Transactional
    @Operation(summary = "Excluir um aluno do sistema", description = "Este endpoint permite deletar um aluno do sistema.")
    @RolesAllowed({"admin"})  // Apenas usuários com role "admin" podem acessar
    @SecurityRequirement(name = "BasicAuth")  // Swagger exige autenticação neste endpoint
    public Response deletarAluno(@QueryParam("nome") String nome) {
        EntityAluno aluno = EntityAluno.find("nome", nome).firstResult();
        if (aluno == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aluno não encontrado").build();
        }
        aluno.delete();
        return Response.ok().build();
    }
}
