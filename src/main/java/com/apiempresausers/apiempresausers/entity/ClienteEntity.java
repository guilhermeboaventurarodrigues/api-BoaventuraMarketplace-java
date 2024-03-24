package com.apiempresausers.apiempresausers.entity;

import com.apiempresausers.apiempresausers.entity.enums.SegmentoClienteEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
@JsonPropertyOrder({"id",  "login", "password", "nome", "dataDeNascimento", "cpf", "segmentoCliente"})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteEntity extends PessoaSuperClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SegmentoClienteEnum segmentoCliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "dono_produto_id")
    private List<ProdutosEntity> produtos_anunciados = new ArrayList<ProdutosEntity>();
}
