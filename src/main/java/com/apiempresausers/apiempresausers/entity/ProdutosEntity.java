package com.apiempresausers.apiempresausers.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String nome_produto;

    private String descricao_produto;

    private double valor_produto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dono_produto_id", nullable = false)
    private ClienteEntity dono_produto_id;
}
