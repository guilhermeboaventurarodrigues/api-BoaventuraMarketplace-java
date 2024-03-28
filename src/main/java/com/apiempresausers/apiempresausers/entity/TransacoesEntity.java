package com.apiempresausers.apiempresausers.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="transacoes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacoesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_pedido;

    @ManyToOne
    @JoinColumn(name = "antigo_dono_produto_id")
    private ClienteEntity antigo_dono_produto_id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutosEntity produto;

    @ManyToOne
    @JoinColumn(name = "novo_dono_produto_id")
    private ClienteEntity novo_dono_produto_id;
}
