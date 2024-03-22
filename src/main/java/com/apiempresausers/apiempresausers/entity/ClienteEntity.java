package com.apiempresausers.apiempresausers.entity;

import jakarta.persistence.*;

@Entity
@Table(name="clientes")
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
