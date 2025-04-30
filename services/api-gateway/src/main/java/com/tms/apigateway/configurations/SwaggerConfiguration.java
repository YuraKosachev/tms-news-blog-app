package com.tms.apigateway.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@OpenAPIDefinition( info = @Info( contact = @Contact(
                                        name = "teach me skills developers",
                                        email = "zanoza.by@gmail.com"
                                        ),
        description = "News data collection and aggregation api service",
        title = "News portal api",
        version = "1.0",
        license = @License(
                name = "I sure we have license",
                url = "https://our-license-url.com"
        ),
        termsOfService = "Terms of service :P"
)
 //       ,
//        security = {
//                @SecurityRequirement(name = "keycloak_security_auth")
//        }

)
//@SecurityScheme(
//        name = "keycloak_security_auth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(
//                clientCredentials  = @OAuthFlow(
//                        authorizationUrl = "${authority.oauth2.auth}",
//                        scopes = { @OAuthScope(name = "read_access", description = "read data"),
//                                   @OAuthScope(name = "write_access", description = "modify data")
//                        })
//        ))

public class SwaggerConfiguration {
}