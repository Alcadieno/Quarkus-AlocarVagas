package alcadienoquarkus.exceptions.exceptionsMappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public interface GenericExceptionMapper<T extends Exception > extends ExceptionMapper<T> {
    //Dark PATTERN
    //Herança travestida de composição ( Loucura isso aqui )
    //Interface com um metodo default nao implementa  um metodo abstrato de outra interface
    default Response buildResponse(T exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\": \"" + exception.getMessage() + "\"}")
                .build();
    }

    @Override
    default Response toResponse(T exception) {
        return buildResponse(exception);
    }
    
}
