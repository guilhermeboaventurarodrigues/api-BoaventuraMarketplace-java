package com.apiBoaventuraMarketplace.controller;

import com.apiBoaventuraMarketplace.entity.dto.GetProdutosDTO;
import com.apiBoaventuraMarketplace.entity.dto.SetProdutosDTO;
import com.apiBoaventuraMarketplace.entity.dto.UpdateProdutosDTO;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import com.apiBoaventuraMarketplace.service.ProdutosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints de produtos")
public class ProdutosController {
    private ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProdutosEntity> create(@RequestBody @Valid SetProdutosDTO prod) {
        ProdutosEntity prodCreate = produtosService.create(prod);
        return ResponseEntity.status(201).body(prodCreate);
    }

    @GetMapping("/get")
    ResponseEntity<List<GetProdutosDTO>> listAll() {
        List<GetProdutosDTO> prods = produtosService.listAll();
        return ResponseEntity.status(200).body(prods);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<GetProdutosDTO> listById(@PathVariable("id") Long id){
        GetProdutosDTO listed = produtosService.listById(id);
        return ResponseEntity.status(200).body(listed);
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<ProdutosEntity> update(@RequestBody UpdateProdutosDTO prod, @PathVariable("id") Long id){
        ProdutosEntity updated = produtosService.update(prod, id);
        return ResponseEntity.status(200).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        produtosService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/anunciar/{id}")
    ResponseEntity<GetProdutosDTO> anunciarProduto(@RequestBody SetProdutosDTO produto, @PathVariable("id") Long id){
        GetProdutosDTO produtoAtualizado = produtosService.anunciarProdutos(produto, id);
        return ResponseEntity.status(201).body(produtoAtualizado);
    }
}
