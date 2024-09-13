package br.com.fiap.sprintjava.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseTokenDTO(
        @Schema(description = "Token de autenticação.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
        String token
) { }