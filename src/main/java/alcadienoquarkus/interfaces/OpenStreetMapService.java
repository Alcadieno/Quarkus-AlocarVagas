package alcadienoquarkus.interfaces;

public interface OpenStreetMapService {
    String buscarCoordenadas(String endereco);

    String buscarEndereco(String latitude, String longitude);
}
