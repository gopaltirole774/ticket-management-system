package com.org.ticketmanagementsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ticket Management System API",
                description = "API documentation for Ticket Management System",
                contact = @Contact(
                        name = "Gopal",
                        email = "gopaltirole774@gmail.com"
                )
        )
)

public class OpenApiConfig {
}
