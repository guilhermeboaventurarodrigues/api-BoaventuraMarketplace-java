package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioEntity> create(FuncionarioEntity func){
        funcionarioRepository.save(func);
        return list();
    }

    public List<FuncionarioEntity> list(){
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioEntity> listById(Long id){
        return funcionarioRepository.findById(id);
    }

    public List<FuncionarioEntity> update(FuncionarioEntity func){
        funcionarioRepository.save(func);
        return list();
    }

    public List<FuncionarioEntity> delete(Long id){
        funcionarioRepository.deleteById(id);
        return list();
    }

}
