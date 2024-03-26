package com.apiempresausers.apiempresausers.controller;


import com.apiempresausers.apiempresausers.entity.dto.GetPedidosDTO;
import com.apiempresausers.apiempresausers.entity.dto.SetPedidosDTO;
import com.apiempresausers.apiempresausers.service.PedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    private PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @PostMapping("/comprar")
    public ResponseEntity<String> comprarProduto(@RequestBody SetPedidosDTO pedidos) {
        return ResponseEntity.status(200).body(pedidosService.comprarProduto(pedidos));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GetPedidosDTO>> listarTransacoes(){
        return ResponseEntity.status(200).body(pedidosService.listarTransacoes());
    }
}
