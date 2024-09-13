package br.com.fiap.sprintjava.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "GeniusXP",
                version = "1.0",
                description = "Uma plataforma centralizada para gestão de eventos que simplifica operações como inscrições, pagamentos e check-in, enquanto aumenta o engajamento com enquetes e networking. A inteligência artificial da GeniusXP utiliza as preferências dos usuários para oferecer uma experiência altamente personalizada e otimizar o planejamento. Com análise de sentimento e assistência virtual, a plataforma proporciona interações mais significativas, elevando a eficiência da gestão e tornando os eventos mais impactantes para cada participante."
        ),
        servers = {
                @Server(description = "Ambiente de Desenvolvimento", url = "http://localhost:8080")
        },
        security = @SecurityRequirement(name = "Bearer Auth")
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "Autenticação básica via Bearer Token JWT.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig { }
