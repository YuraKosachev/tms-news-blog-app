package com.tms.dataprovider.configurations;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "teach me skills developers",
                        email = "zanoza.by@gmail.com"
                ),
                description = "A simple imitation of the dzen api",
                title = "Data provider api",
                version = "1.0",
                license = @License(
                        name = "I sure we have license",
                        url = "https://our-license-url.com"
                ),
                termsOfService = "Terms of service :P"
        )

)
public class SwaggerConfiguration {}


//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.models.security.Scopes;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.extensions.Extension;
//import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.info.License;
//import io.swagger.v3.oas.annotations.security.*;
//import io.swagger.v3.oas.annotations.servers.Server;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(
//                        name = "teach me skills developers",
//                        email = "zanoza.by@gmail.com"
//                ),
//                description = "A simple imitation of the dzen api",
//                title = "Data provider api",
//                version = "1.0",
//                license = @License(
//                        name = "I sure we have license",
//                        url = "https://our-license-url.com"
//                ),
//                termsOfService = "Terms of service :P"
//        ),
//        servers = {
//                @Server(url = "http://localhost:8222", description = "Gateway")
//        },
//        security = {
//                @SecurityRequirement(name = "keycloak_security_auth")
//        }
//
//)
//@SecurityScheme(
//        name = "keycloak_security_auth",
//        type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(
//                //implicit =
//                password = @OAuthFlow(
//                        authorizationUrl = "${authority.oauth2.auth}",
//                        extensions = {
//                          @Extension(properties = )
//                        },
                        //tokenUrl = "http://localhost:9098/realms/news-microservices/protocol/openid-connect/token",
                        //refreshUrl = "",
//                        scopes = { @OAuthScope(name = "read_access", description = "read data"),
//                                @OAuthScope(name = "write_access", description = "modify data"),
//
//                        }
//                )//,

//                implicit = @OAuthFlow(
//
//                        authorizationUrl = "${authority.oauth2.auth}",
//                        tokenUrl = "http://localhost:9098/realms/news-microservices/protocol/openid-connect/token",
//                        //refreshUrl = "",
//                        scopes = { @OAuthScope(name = "read_access", description = "read data"),
//                                @OAuthScope(name = "write_access", description = "modify data"),
//
//                        })
 //       ))

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
////import io.swagger.v3.oas.annotations.security.OAuthFlow;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//public class SwaggerConfiguration {
//}
//@Configuration
//@OpenAPIDefinition
//public class SwaggerConfiguration {
//    private String authServerUrl = "http://localhost:9098";
//    private String realm="news-microservices";
//           private final String OAUTH_SCHEME_NAME = "keycloak_security_auth";
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().components(new Components()
//                        .addSecuritySchemes(OAUTH_SCHEME_NAME, createOAuthScheme()))
//                .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME))
//                .info(new Info().title("Todos Management Service")
//                        .description("A service providing todos.")
//                        .version("1.0"));
//    }
//
//    private SecurityScheme createOAuthScheme() {
//        OAuthFlows flows = createOAuthFlows();
//        return new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
//                .flows(flows);
//    }
//
//    private OAuthFlows createOAuthFlows() {
//        OAuthFlow flow = createAuthorizationCodeFlow();
//        return new OAuthFlows().implicit(flow);
//    }
//
//    private OAuthFlow createAuthorizationCodeFlow() {
//        return new OAuthFlow()
//                .authorizationUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/auth")
//                .scopes(new Scopes().addString("read_access", "read data")
//                        .addString("write_access", "modify data"));
 //   }
//    private static final String OPEN_ID_SCHEME_NAME = "openId";
//    //"realms/news-microservices/.well-known/openid-configuration"
//    private static final String OPENID_CONFIG_FORMAT = "%s/realms/%s/.well-known/openid-configuration";
//
//    @Bean
//    OpenAPI customOpenApi() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(OPEN_ID_SCHEME_NAME, createOpenIdScheme()))
//                .addSecurityItem(new SecurityRequirement().addList(OPEN_ID_SCHEME_NAME));
//    }
//
//    private SecurityScheme createOpenIdScheme() {
//        String connectUrl = String.format(OPENID_CONFIG_FORMAT, "http://localhost:9098", "news-microservices");
//
//        return new SecurityScheme()
//                .type(SecurityScheme.Type.OPENIDCONNECT)
//                .openIdConnectUrl(connectUrl);
//    }
//}