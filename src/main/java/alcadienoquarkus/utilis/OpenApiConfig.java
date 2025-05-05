package alcadienoquarkus.utilis;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(title = "Quarkus-Alocar Vagas", version = "1.0", 
    description = "Conjunto de API's para gerenciamento de vagas de estágio de uma instituição de ensino."),
    security = @SecurityRequirement(name = "BasicAuth")
)
@SecurityScheme(
    securitySchemeName = "BasicAuth",    scheme = "basic",
    type = SecuritySchemeType.HTTP
)
public class OpenApiConfig extends Application {
}