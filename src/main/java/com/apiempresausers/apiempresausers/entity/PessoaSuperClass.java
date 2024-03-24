package com.apiempresausers.apiempresausers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaSuperClass {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private String dataDeNascimento;
    @NotNull
    @NotEmpty
    private String cpf;
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    private String password;
}
