package com.tms.gateway.configurations;

import com.tms.gateway.core.constants.SecurityConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition( info = @Info(
        contact = @Contact(
                name = "teach me skills developers",
                email = "zanoza.by@gmail.com"
        ),
        description = "News api",
        title = "News api",
        version = "1.0",
        license = @License(
                name = "I sure we have license",
                url = "https://our-license-url.com"
        ),
        termsOfService = "Terms of service :P"
))
@SecurityScheme(
        name = SecurityConstants.OAUTH2_NAME,
        scheme = "bearer",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER,
        flows = @OAuthFlows(password = @OAuthFlow(
                tokenUrl = "${securityschema.flow.tokenUrl}",
                authorizationUrl = "${securityschema.flow.authorizationUrl}")
        )


)
public class SwaggerConfiguration {
}
