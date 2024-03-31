package com.apiBoaventuraMarketplace.entity.dto;

import lombok.Data;

@Data
public class SetPedidosDTO {
    private Long produto_id;
    private Long novo_dono_produto_id;
    private Long antigo_dono_produto_id;
}
