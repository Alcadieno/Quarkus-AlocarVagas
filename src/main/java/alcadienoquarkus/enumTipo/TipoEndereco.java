package alcadienoquarkus.enumTipo;


public enum TipoEndereco {
    POSTCODE("postcode"),
    STREET("road");

 public String tipo;

private TipoEndereco(String tipo) {
    this.tipo = tipo;
}
    
}
