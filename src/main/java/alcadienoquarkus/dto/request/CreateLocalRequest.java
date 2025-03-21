package alcadienoquarkus.dto.request;

public class CreateLocalRequest {
    
    private String nome;
    private String endereco;
    private int totalVagas;

    
    public CreateLocalRequest(String nome, String endereco,int totalVagas) {
        this.nome = nome;
        this.endereco = endereco;
        this.totalVagas = totalVagas;

    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTotalVagas() {
        return totalVagas;
    }

    public void setTotalVagas(int totalVagas) {
        this.totalVagas = totalVagas;
    }
    
}
