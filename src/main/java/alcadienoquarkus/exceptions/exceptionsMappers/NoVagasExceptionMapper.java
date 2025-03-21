package alcadienoquarkus.exceptions.exceptionsMappers;

import alcadienoquarkus.exceptions.NoVagasException;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NoVagasExceptionMapper implements GenericExceptionMapper<NoVagasException> {

}
