package alcadienoquarkus.interfaces;
/*
 * Essa Classe é responsavel por fazer uma interface com a API do OpenStreetMap
 * é essa classe que diz quais parametros serão passados para a API
 * e quais endoint a Api Do OpenStreetMap vai usar
 */
public interface OpenStreetMapService {
    String buscarCoordenadas(String endereco);

    String buscarEndereco(String latitude, String longitude);
}
