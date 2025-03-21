package alcadienoquarkus.service;


import alcadienoquarkus.interfaces.OpenStreetMapService;
import alcadienoquarkus.utilis.HttpsClientRequest;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OpenStreetMapRestService implements OpenStreetMapService{

    private static final String BASE_URL = "https://nominatim.openstreetmap.org";
    private static final String RETURN_TYPE = "&format=json&limit=1";
    

    @Override
    public String buscarCoordenadas(String endereco) {
        try {
            String url = BASE_URL +"/search" + "?postalcode=" + endereco + RETURN_TYPE;
            HttpsClientRequest request = new HttpsClientRequest();
            
            Response response = request.fazerRequisicao(url);

            String jsonResponse = response.readEntity(String.class);

            JsonArray jsonArray = new JsonArray(jsonResponse);
            if (jsonArray.size() > 0) {
                JsonObject latelongi = jsonArray.getJsonObject(0);
                return latelongi.toString();
            } else {
                return "Endereço não encontrado";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na busca de coordenadas";
        }
    }

    @Override
    public String buscarEndereco(String latitude, String longitude) {
        try {
            HttpsClientRequest request = new HttpsClientRequest();
            String url = BASE_URL + "/reverse"+ "?lat=" + latitude + "&lon=" + longitude + "&format=json&limit=1";

            Response response = request.fazerRequisicao(url);

            String jsonResponse = response.readEntity(String.class);

    
            if (jsonResponse.isEmpty()) {
                return "Endereço não encontrado";
            } else {
                return jsonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na busca de endereço";
        }
    }
}
