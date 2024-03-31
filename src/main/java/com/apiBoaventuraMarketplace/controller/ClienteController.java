package com.apiBoaventuraMarketplace.controller;

import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    ResponseEntity<Boolean> login(@RequestBody ClienteEntity cliente){
        Boolean valid = clienteService.validarSenha(cliente);
        if (!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(valid);
        } return ResponseEntity.status(HttpStatus.ACCEPTED).body(valid);
    }
}
