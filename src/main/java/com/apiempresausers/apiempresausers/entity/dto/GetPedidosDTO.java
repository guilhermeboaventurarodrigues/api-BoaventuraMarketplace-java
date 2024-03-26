package com.apiempresausers.apiempresausers.entity.dto;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
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
