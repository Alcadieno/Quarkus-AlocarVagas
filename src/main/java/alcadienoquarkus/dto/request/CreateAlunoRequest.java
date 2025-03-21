package alcadienoquarkus.dto.request;

public class CreateAlunoRequest {
    
    private String nome;
    private String endereco;
  
    
    public CreateAlunoRequest(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
 
    }

    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
        CreateAlunoRequest other = (CreateAlunoRequest) obj;
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
        return true;
    }

    
    
}
