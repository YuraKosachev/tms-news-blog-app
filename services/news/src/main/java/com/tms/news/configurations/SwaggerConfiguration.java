package com.tms.news.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition( info = @Info(
        contact = @Contact(
                name = "teach me skills developers",
                email = "zanoza.by@gmail.com"
        ),
        description = "A simple imitation of the dzen api",
        title = "News api",
        version = "1.0",
        license = @License(
                name = "I sure we have license",
                url = "https://our-license-url.com"
        ),
        termsOfService = "Terms of service :P"
))
public class SwaggerConfiguration {
}
