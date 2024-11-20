package com.apiBoaventuraMarketplace.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

// DTO de Login
@Data
public class LoginRequest {
    @NotEmpty(message = "Login não pode ser vazio")
    private String login;

    @NotEmpty(message = "Senha não pode ser vazia")
    private String password;
}
