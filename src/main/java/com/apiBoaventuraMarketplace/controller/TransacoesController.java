package com.apiBoaventuraMarketplace.controller;


import com.apiBoaventuraMarketplace.entity.dto.GetPedidosDTO;
import com.apiBoaventuraMarketplace.entity.dto.SetPedidosDTO;
import com.apiBoaventuraMarketplace.service.TransacoesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Endpoints de transações")
public class TransacoesController {
    private TransacoesService transacoesService;

    public TransacoesController(TransacoesService transacoesService) {
        this.transacoesService = transacoesService;
    }

    @PostMapping("/comprar")
    public ResponseEntity<String> comprarProduto(@RequestBody SetPedidosDTO pedidos) {
        return ResponseEntity.status(201).body(transacoesService.comprarProduto(pedidos));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GetPedidosDTO>> listarTransacoes(){
        return ResponseEntity.status(200).body(transacoesService.listarTransacoes());
    }
}
