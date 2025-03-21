package alcadienoquarkus.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import alcadienoquarkus.model.EntityLocal;
import alcadienoquarkus.valueObject.Coordenada;
import io.quarkus.hibernate.orm.panache.PanacheQuery;


@ApplicationScoped
public class CoodernadaService {

    public CoodernadaService() {
    }

    public Coordenada menorDistancia(Coordenada coordenada, List<Coordenada> coordenadas) {
        Float menorDistancia = Float.MAX_VALUE;
        Coordenada coordenadaMenorDistancia = null;
        for(Coordenada c : coordenadas) {
            Float distancia = haversineDistancia(coordenada.getLatitude(), coordenada.getLongitude(), c.getLatitude(), c.getLongitude());
            if(distancia < menorDistancia) {
                System.out.println("MENOR DISTANCIA EH " + menorDistancia);
                menorDistancia = distancia;
                coordenadaMenorDistancia = new Coordenada(c.getLatitude(), c.getLongitude());
                
            }
        }
        return coordenadaMenorDistancia;
    }


    public Float haversineDistancia(Float lat1, Float lon1, Float lat2, Float lon2) {
        //Converte para radianos
        Float dlat = (float) Math.toRadians(lat2 - lat1);
        Float dlon = (float) Math.toRadians(lon2 - lon1);

        Float a = (float) (Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dlon / 2) * Math.sin(dlon / 2));
        
        Float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

        return (Float) (6371 * c); // Raio da Terra em quilômetros


    }

    public List<Coordenada> obterListaDeCoodernadas(){
        PanacheQuery<EntityLocal> localEntity = EntityLocal.findAll();
        List<Coordenada> coordenadas = new ArrayList<>();

        for (EntityLocal entityLocal : localEntity.list()) {
            Coordenada entidadeApenasCoordenada = new Coordenada(entityLocal.getLatitude(), entityLocal.getLongitude());
            coordenadas.add(entidadeApenasCoordenada);
        }
        return coordenadas;
    }
    
}
