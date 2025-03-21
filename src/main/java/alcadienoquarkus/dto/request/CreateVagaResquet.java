package alcadienoquarkus.dto.request;

import alcadienoquarkus.exceptions.NoVagasException;
import alcadienoquarkus.model.EntityAluno;
import alcadienoquarkus.model.EntityLocal;

public class CreateVagaResquet {

    private EntityAluno aluno;
    private EntityLocal hospital;

    public CreateVagaResquet(EntityAluno aluno, EntityLocal hospital) {
        this.aluno = aluno;
        this.hospital = hospital;
    }

    public Long getIdAluno() {
        return aluno.getId();
    }
    
    public Long getIdHospital() {
        return hospital.getId();
    }

    public boolean verificarVaga(){
        if(this.hospital.getTotalVagas() > 0){
            System.out.println("PODE ALOCAR");
            return true;
        } else{
            throw new NoVagasException("Hospital sem vagas disponiveis");
        }
    }

    
    
}
