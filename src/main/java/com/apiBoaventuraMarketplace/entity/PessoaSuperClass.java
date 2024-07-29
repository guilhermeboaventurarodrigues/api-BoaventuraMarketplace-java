package com.apiBoaventuraMarketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(unique = true)
    private String login;

    @NotNull
    @NotEmpty
    private String password;
}