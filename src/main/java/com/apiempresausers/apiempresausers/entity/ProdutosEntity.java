package com.apiempresausers.apiempresausers.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String nome_produto;

    private String descricao_produto;

    private double valor_produto;

    private boolean isOfferActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dono_produto_id", nullable = false)
    private ClienteEntity dono_produto_id;
}
