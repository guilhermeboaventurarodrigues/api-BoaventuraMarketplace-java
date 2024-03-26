package com.apiempresausers.apiempresausers.entity.dto;

import lombok.Data;

@Data
public class SetProdutosDTO {
    private Long id;
    private String nome_produto;
    private String descricao_produto;
    private double valor_produto;
    private boolean isOfferActive;
    private Long dono_produto_id;
}

