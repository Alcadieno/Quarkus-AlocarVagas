package alcadienoquarkus.factory;

import alcadienoquarkus.interfaces.OpenStreetMapService;
import alcadienoquarkus.service.OpenStreetMapRestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class OpenStreetMapFactory {
    
    @Produces
    public static OpenStreetMapService criarServico() {
        return new OpenStreetMapRestService();
    }
}
