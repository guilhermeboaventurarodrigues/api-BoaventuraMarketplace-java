package com.apiempresausers.apiempresausers.controller;

import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.service.FuncionarioService;
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
    List<FuncionarioEntity> create(@RequestBody FuncionarioEntity func){
        return funcionarioService.create(func);
    }

    @GetMapping("/get")
    List<FuncionarioEntity> list(){
        return funcionarioService.list();
    }

    @GetMapping("/get/{id}")
    Optional<FuncionarioEntity> listById(@PathVariable("id") Long id){
        return funcionarioService.listById(id);
    }

    @PutMapping("/put")
    List<FuncionarioEntity> update(@RequestBody FuncionarioEntity func){
        return funcionarioService.update(func);
    }

    @DeleteMapping("{id}")
    List<FuncionarioEntity> delete(@PathVariable("id") Long id){
        return funcionarioService.delete(id);
    }
}
