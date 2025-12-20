package cz.buben.learning.habits.habitservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Ivo Slanina",
            email = "ivo.slanina@gmail.com"
        ),
        description = "Habit Service API Documentation",
        title = "Habit Service API",
        version = "1.0",
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        ),
        termsOfService = "https://swagger.io/terms/"
    ),
    servers = {
        @Server(
            description = "Local Server",
            url = "http://localhost:8080"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT Bearer token authentication",
    scheme = "bearer",
    type = SecuritySchemeType.OAUTH2,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            authorizationUrl = "http://localhost:8888/realms/habit-realm/protocol/openid-connect/auth",
            tokenUrl = "http://localhost:8888/realms/habit-realm/protocol/openid-connect/token",
            scopes = {
                @OAuthScope(name = "springdoc.read", description = "read scope"),
                @OAuthScope(name = "springdoc.write", description = "write scope"),
                @OAuthScope(name = "openid", description = "OpenID")
            }
        )
    )
)
public class OpenApiConfig {
}
