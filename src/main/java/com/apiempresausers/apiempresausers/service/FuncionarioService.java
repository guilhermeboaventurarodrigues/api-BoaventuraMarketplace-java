package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.repository.FuncionarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    private PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public FuncionarioEntity create(FuncionarioEntity func){
        String encoder = this.passwordEncoder.encode(func.getPassword());
        func.setPassword(encoder);
        FuncionarioEntity funcionarioEntity = funcionarioRepository.save(func);
        return funcionarioEntity;
    }

    public List<FuncionarioEntity> list(){
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioEntity> listById(Long id){
        return funcionarioRepository.findById(id);
    }

    public List<FuncionarioEntity> update(FuncionarioEntity func){
        String encoder = this.passwordEncoder.encode(func.getPassword());
        func.setPassword(encoder);
        funcionarioRepository.save(func);
        return list();
    }

    public List<FuncionarioEntity> delete(Long id){
        funcionarioRepository.deleteById(id);
        return list();
    }

    public Boolean validarSenha(FuncionarioEntity func){
        String senha = funcionarioRepository.getById(func.getId()).getPassword();
        Boolean valid = passwordEncoder.matches(func.getPassword(), senha);
        return valid;
    }
}
