package com.apiempresausers.apiempresausers.entity;

import com.apiempresausers.apiempresausers.entity.enums.DepartamentoFuncionarioEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonPropertyOrder({"id",  "login", "password", "nome", "dataDeNascimento", "cpf", "departamentoEnum", "salario"})
@Table(name="funcionarios")
public class FuncionarioEntity extends PessoaSuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double salario;
    @NotNull
    private DepartamentoFuncionarioEnum departamentoEnum;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public DepartamentoFuncionarioEnum getDepartamentoEnum() {
        return departamentoEnum;
    }

    public void setDepartamentoEnum(DepartamentoFuncionarioEnum departamentoInterface) {
        this.departamentoEnum = departamentoInterface;
    }
}
