package com.apiempresausers.apiempresausers.controller;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.service.ClienteService;
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
    ClienteEntity create(@RequestBody ClienteEntity cliente){
        return clienteService.create(cliente);
    }

    @GetMapping("/get")
    List<ClienteEntity> listAll(){
        return clienteService.listAll();
    }

    @GetMapping("/get/{id}")
    Optional<ClienteEntity> listById(@PathVariable("id") Long id){
        return clienteService.listById(id);
    }

    @PutMapping("/edit")
    String update(ClienteEntity cliente){
        return clienteService.update(cliente);
    }

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable("id") Long id){
        return clienteService.delete(id);
    }
}
