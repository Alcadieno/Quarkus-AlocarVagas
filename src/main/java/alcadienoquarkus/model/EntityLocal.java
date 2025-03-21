package alcadienoquarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "HOSPITAL")
public class EntityLocal extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    //talvez divir o tipo de endereço em CEP e logradouro ? ;
    private String endereco;
    private Float latitude;
    private Float longitude;
    
    @Column(name = "TOTAL_VAGAS")
    private int totalVagas;


    public EntityLocal() {
    }
    
    public EntityLocal(@NotBlank String nome,
                    @NotBlank String endereco,
                    @NotBlank int totalVagas,
                    @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Float latitude,
                    @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Float longitude) {
        this.nome = nome;
        this.endereco = endereco;
        this.totalVagas = totalVagas;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public int getTotalVagas() {
        return totalVagas;
    }

    public void setTotalVagas(int totalVagas) {
        this.totalVagas = totalVagas;
    }
    public Long getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public record Coodernada(Double latitude, Double longitude) {
    }

    @Override
    public String toString() {
        return "EntityHospital [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
        EntityLocal other = (EntityLocal) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (latitude == null) {
            if (other.latitude != null)
                return false;
        } else if (!latitude.equals(other.latitude))
            return false;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        return true;
    }
    
}
