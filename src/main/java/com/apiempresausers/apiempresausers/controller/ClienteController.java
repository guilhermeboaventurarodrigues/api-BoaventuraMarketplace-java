package com.apiempresausers.apiempresausers.controller;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create")
    ResponseEntity<ClienteEntity> create(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.status(201).body(clienteService.create(cliente));
    }

    @GetMapping("/get")
    ResponseEntity<List<ClienteEntity>> listAll() {
        return ResponseEntity.status(200).body(clienteService.listAll());
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ClienteEntity> listById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(clienteService.listById(id));
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<ClienteEntity> update(@RequestBody ClienteEntity cliente, @PathVariable("id") Long id) {
        ClienteEntity updated  = clienteService.update(cliente, id);
        return ResponseEntity.status(201).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
