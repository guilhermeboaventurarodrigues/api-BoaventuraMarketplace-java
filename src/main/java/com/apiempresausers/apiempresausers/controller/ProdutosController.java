package com.apiempresausers.apiempresausers.controller;

import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import com.apiempresausers.apiempresausers.service.ProdutosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    private ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping("/create")
    ResponseEntity<ProdutosEntity> create(@RequestBody ProdutosEntity prod) {
        ProdutosEntity prodCreate = produtosService.create(prod);
        return ResponseEntity.status(201).body(prodCreate);
    }

    @GetMapping("/get")
    ResponseEntity<List<ProdutosEntity>> listAll() {
        List<ProdutosEntity> prods = produtosService.listAll();
        return ResponseEntity.status(200).body(prods);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ProdutosEntity> listById(@PathVariable("id") Long id){
        ProdutosEntity listed = produtosService.listById(id);
        return ResponseEntity.status(200).body(listed);
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<ProdutosEntity> update(@RequestBody ProdutosEntity prod, @PathVariable("id") Long id){
        ProdutosEntity updated = produtosService.update(prod, id);
        return ResponseEntity.status(200).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        produtosService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
