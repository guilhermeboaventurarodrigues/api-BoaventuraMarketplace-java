package com.apiempresausers.apiempresausers.controller;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/funcionarios")
public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/create")
    ResponseEntity<FuncionarioEntity> create(@RequestBody FuncionarioEntity func){
        return ResponseEntity.status(201).body(funcionarioService.create(func));
    }

    @GetMapping("/get")
    ResponseEntity<List<FuncionarioEntity>> list(){
        return ResponseEntity.status(200).body(funcionarioService.list());
    }

    @GetMapping("/get/{id}")
    ResponseEntity<Optional<FuncionarioEntity>> listById(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(funcionarioService.listById(id));
    }

    @PutMapping("/put")
    ResponseEntity<List<FuncionarioEntity>> update(@RequestBody FuncionarioEntity func){
        return ResponseEntity.status(200).body(funcionarioService.update(func));
    }

    @DeleteMapping("{id}")
   ResponseEntity<?> delete(@PathVariable("id") Long id){
        funcionarioService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    ResponseEntity<FuncionarioEntity> validarSenha(@RequestBody FuncionarioEntity func){
        Boolean valid = funcionarioService.validarSenha(func);
        if (!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } return ResponseEntity.status(200).build();
    }
}
