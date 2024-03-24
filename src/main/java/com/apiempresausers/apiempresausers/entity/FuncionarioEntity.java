package com.apiempresausers.apiempresausers.entity;

import com.apiempresausers.apiempresausers.entity.enums.DepartamentoFuncionarioEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonPropertyOrder({"id",  "login", "password", "nome", "dataDeNascimento", "cpf", "departamentoEnum", "salario"})
@Table(name="funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioEntity extends PessoaSuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double salario;
    @NotNull
    private DepartamentoFuncionarioEnum departamentoEnum;
}
