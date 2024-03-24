package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Transactional
    public FuncionarioEntity create(FuncionarioEntity func) {
        String encoder = this.passwordEncoder.encode(func.getPassword());
        func.setPassword(encoder);
        return funcionarioRepository.save(func);
    }

    public FuncionarioEntity listById(Long id) {
        Optional<FuncionarioEntity> funcionarioListed = this.funcionarioRepository.findById(id);
        return funcionarioListed.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrato"
        ));
    }

    public List<FuncionarioEntity> list() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public FuncionarioEntity update(FuncionarioEntity func, Long id) {
        listById(id);
        String encoder = this.passwordEncoder.encode(func.getPassword());
        func.setPassword(encoder);
        return func;
    }

    public void delete(Long id) {
        listById(id);
        try {
            funcionarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel excluir pois não encontramos esse ID no nosso banco de dados!");
        }
    }

    public Boolean validarSenha(FuncionarioEntity func) {
        String senha = funcionarioRepository.getById(func.getId()).getPassword();
        Boolean valid = passwordEncoder.matches(func.getPassword(), senha);
        return valid;
    }
}
