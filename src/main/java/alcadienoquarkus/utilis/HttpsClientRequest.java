package alcadienoquarkus.utilis;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HttpsClientRequest {

    public Response fazerRequisicao(String url) {

        Client client = ClientBuilder.newClient();

        //Printando a url para ver se esta tudo certo
        System.out.println("URL: " + url);

        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "Mozilla/5.0")
                .get(); 

        System.out.println("Resposta: " + response.readEntity(String.class));
        client.close();
        return response;
    }

}
