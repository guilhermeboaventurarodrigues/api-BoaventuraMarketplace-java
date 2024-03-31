package com.apiBoaventuraMarketplace.entity.dto;

import lombok.Data;

@Data
public class UpdateProdutosDTO {
    private String nome_produto;
    private String descricao_poduto;
    private double valor_produto;
}
