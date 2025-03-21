package alcadienoquarkus.model;


import java.sql.Timestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "VAGA")
public class EntityVaga extends PanacheEntityBase{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HOSPITAL_ID")
    private Long idHospital;
    @Column(name = "ALUNO_ID")
    private Long idAluno;
    @Column(name = "DATA_ALOCACAO")
    private Timestamp dataAlocacao;
    
    
    public EntityVaga() {
    }


    public EntityVaga(Long idHospital, Long idAluno) {
        this.idHospital = idHospital;
        this.idAluno = idAluno;
        this.dataAlocacao = new Timestamp(System.currentTimeMillis());
    }

    public Long getIdHospital() {
        return idHospital;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idHospital == null) ? 0 : idHospital.hashCode());
        result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityVaga other = (EntityVaga) obj;
        if (idHospital == null) {
            if (other.idHospital != null)
                return false;
        } else if (!idHospital.equals(other.idHospital))
            return false;
        if (idAluno == null) {
            if (other.idAluno != null)
                return false;
        } else if (!idAluno.equals(other.idAluno))
            return false;
        return true;
    }

    

    
    
}
