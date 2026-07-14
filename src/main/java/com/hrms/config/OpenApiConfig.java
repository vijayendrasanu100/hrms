package com.hrms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hrmsOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("HRMS REST API")
                                .version("v1.0")
                                .description("""
                                        Human Resource Management System REST APIs.
                                        
                                        Features:
                                        • Department Management
                                        • Designation Management
                                        • Employee Management
                                        • Leave Management
                                        • Payroll Management
                                        """)
                                .contact(
                                        new Contact()
                                                .name("HRMS Development Team")
                                                .email("support@hrms.com")
                                )
                );
    }
}