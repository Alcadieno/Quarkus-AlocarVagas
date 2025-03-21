package alcadienoquarkus.service;

import java.io.IOException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import alcadienoquarkus.model.EntityAluno;
import alcadienoquarkus.model.EntityLocal;
import alcadienoquarkus.model.EntityVaga;
import alcadienoquarkus.dto.request.CreateAlunoRequest;
import alcadienoquarkus.dto.request.CreateLocalRequest;
import alcadienoquarkus.dto.request.CreateVagaResquet;
import alcadienoquarkus.dto.request.response.OpenStreetMapResponseReverse;
import alcadienoquarkus.dto.request.response.OpenStreetMapServiceResponseSearch;
import alcadienoquarkus.exceptions.VagaOcupadaException;
import alcadienoquarkus.factory.OpenStreetMapFactory;
import alcadienoquarkus.interfaces.OpenStreetMapService;

import alcadienoquarkus.valueObject.Coordenada;
import jakarta.enterprise.context.ApplicationScoped;



@ApplicationScoped
public class EstagioService {

    public EstagioService() {

    }

    public EntityLocal criarLocal(CreateLocalRequest request) throws IOException {

        OpenStreetMapService service = OpenStreetMapFactory.criarServico();

        String coordenadas = service.buscarCoordenadas(request.getEndereco());

        List<OpenStreetMapServiceResponseSearch> response = OpenStreetMapServiceResponseSearch
                .fromString(coordenadas);
        
                //Testando essa loucura aqui 
        String logradouro = service.buscarEndereco(response.get(0).getLat(),
        response.get(0).getLon());

        OpenStreetMapResponseReverse logradouroEmClasse =  OpenStreetMapResponseReverse.fromJson(logradouro);
        System.out.println(logradouroEmClasse.getAddress().getRoad());
        System.out.println(logradouroEmClasse.getAddress().getCity());
        System.out.println(logradouroEmClasse.getAddress().getCountry());
        System.out.println(logradouroEmClasse.getAddress().getState());

        String montandoEnderecoNamao =  logradouroEmClasse.getAddress().getRoad() + " - " + logradouroEmClasse.getAddress().getCity() + ","  + logradouroEmClasse.getAddress().getState()+" - " + logradouroEmClasse.getAddress().getCountry();

        Coordenada coordenadaEmFloat = Coordenada.parseStringToFloat(response.get(0).getLat(),
                response.get(0).getLon());

        EntityLocal localEntity = new EntityLocal(request.getNome(),montandoEnderecoNamao,
                request.getTotalVagas(),
                coordenadaEmFloat.getLatitude(), coordenadaEmFloat.getLongitude());

        localEntity.persist();

        return localEntity;

    }

    public EntityVaga alocarAlunoAVaga(String nomeAluno, String nomeLocal) {

        EntityLocal local = EntityLocal.find("nome", nomeLocal).firstResult();
        EntityAluno aluno = EntityAluno.find("nome", nomeAluno).firstResult();

        CreateVagaResquet vaga = new CreateVagaResquet(aluno, local);
        try {
            if (vaga.verificarVaga()) {
                local.setTotalVagas(local.getTotalVagas() - 1);
                local.persist();
                EntityVaga vagaEntity = new EntityVaga(local.getId(), aluno.getId());
                vagaEntity.persist();
                return vagaEntity;
            }
        } catch (ConstraintViolationException e) {
            if (e.getSQLException().getErrorCode() == 1) { // ORA-00001: unique constraint violated - erro proprio do oracle                                             // oracle
                throw new VagaOcupadaException("Esse aluno ja esta alocado em um vaga.");
            }
            
        }
        return null; // ta feio isso aqui mas é so pra nao dar erro
    }

    public EntityAluno criarAluno(CreateAlunoRequest alunoRequest) throws JsonMappingException, JsonProcessingException {
       
        OpenStreetMapService service = OpenStreetMapFactory.criarServico();
        String coordenadaArray = service.buscarCoordenadas(alunoRequest.getEndereco());
        List<OpenStreetMapServiceResponseSearch> response = OpenStreetMapServiceResponseSearch
                .fromString(coordenadaArray);
        
        if (response.isEmpty()) {// Melhorar isso aqui
            throw new RuntimeException("Endereço não encontrado");
        }
        Coordenada coordenadaEmFloat = Coordenada.parseStringToFloat(response.get(0).getLat(),
                response.get(0).getLon());

        EntityAluno entityAluno = new EntityAluno(alunoRequest.getNome(), alunoRequest.getEndereco(),
                coordenadaEmFloat.getLatitude(), coordenadaEmFloat.getLongitude());
        entityAluno.persist();

        return entityAluno;
    }

}
