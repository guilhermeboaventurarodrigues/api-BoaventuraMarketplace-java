package com.apiBoaventuraMarketplace.entity.dto;

import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPedidosDTO {
    private Long id;
    private long data_pedido;
    private ClienteEntity antigo_dono_produto;
    private ProdutosEntity produto;
    private ClienteEntity novo_dono_produto;
}
