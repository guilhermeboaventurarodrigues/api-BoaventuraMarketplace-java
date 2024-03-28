package com.apiempresausers.apiempresausers.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcessDTO {
    private String token;

    //TODO implementar retornar o usuario e liberações (authorities)
}
