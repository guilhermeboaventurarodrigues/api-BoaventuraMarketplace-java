package com.apiempresausers.apiempresausers.entity.dto;

import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import lombok.Data;

@Data
public class GetProdutosDTO {
    private Long id;
    private String nome_produto;
    private String descricao_produto;
    private double valor_produto;
    private Long dono_produto_id;
    private boolean isOfferActive;

    public GetProdutosDTO(ProdutosEntity produto){
        this.id = produto.getId();
        this.nome_produto = produto.getNome_produto();
        this.descricao_produto = produto.getDescricao_produto();
        this.valor_produto = produto.getValor_produto();
        this.dono_produto_id = produto.getDono_produto_id().getId();
        this.isOfferActive = produto.isOfferActive();
    }
}
