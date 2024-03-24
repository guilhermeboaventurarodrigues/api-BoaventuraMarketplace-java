package com.apiempresausers.apiempresausers.entity;

import com.apiempresausers.apiempresausers.entity.enums.SegmentoClienteEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name="clientes")
@JsonPropertyOrder({"id",  "login", "password", "nome", "dataDeNascimento", "cpf", "segmentoCliente"})
public class ClienteEntity extends PessoaSuperClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SegmentoClienteEnum segmentoCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SegmentoClienteEnum getSegmentoCliente() {
        return segmentoCliente;
    }

    public void setSegmentoCliente(SegmentoClienteEnum segmentoCliente) {
        this.segmentoCliente = segmentoCliente;
    }
}
