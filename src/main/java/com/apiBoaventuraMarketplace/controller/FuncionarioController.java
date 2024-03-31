package com.apiBoaventuraMarketplace.controller;

import com.apiBoaventuraMarketplace.entity.FuncionarioEntity;
import com.apiBoaventuraMarketplace.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/funcionarios")
public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/create")
    ResponseEntity<FuncionarioEntity> create(@RequestBody FuncionarioEntity func){
        FuncionarioEntity create = funcionarioService.create(func);
        return ResponseEntity.status(201).body(create);
    }

    @GetMapping("/get")
    ResponseEntity<List<FuncionarioEntity>> listAll(){
        List<FuncionarioEntity> list = funcionarioService.listAll();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<FuncionarioEntity> listById(@PathVariable("id") Long id){
        FuncionarioEntity listById = funcionarioService.listById(id);
        return ResponseEntity.status(200).body(listById);
    }

    @PutMapping("/put/{id}")
    ResponseEntity<FuncionarioEntity> update(@RequestBody FuncionarioEntity func, @PathVariable("id") Long id){
        FuncionarioEntity update = funcionarioService.update(func, id);
        return ResponseEntity.status(200).body(update);
    }

    @DeleteMapping("/delete/{id}")
   ResponseEntity<Void> delete(@PathVariable("id") Long id){
        funcionarioService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    ResponseEntity<Boolean> validarSenha(@RequestBody FuncionarioEntity func){
        Boolean valid = funcionarioService.validarSenha(func);
        if (!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } return ResponseEntity.status(200).body(valid);
    }
}
