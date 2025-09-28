package com.microservices.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Employee Api specification", description = "Api documentation for employee service", version = "1.0", contact = @Contact(name = "Nguyen Thanh Hoan ", email = "nguyenthanhhoan300904@gmail.com", url = "nguyenthanhhoan"

),

        license = @License(name = "hoan", url = "nguyenthanhhoan.cloud"), termsOfService = "no"

), servers = {
        @Server(description = "Local ENV", url = "http://localhost:9002"),
        @Server(description = "Dev ENV", url = "http://employess-service.dev.com"),
        @Server(description = "Prod ENV", url = "http://employess-service.prod.com")
})
public class OpenApiConfig {

}
