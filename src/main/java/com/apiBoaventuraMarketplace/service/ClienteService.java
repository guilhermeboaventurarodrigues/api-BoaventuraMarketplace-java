package com.apiBoaventuraMarketplace.service;

import com.apiBoaventuraMarketplace.entity.dto.LoginRequest;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    private PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public ClienteEntity create(ClienteEntity cliente){
         String encoder = passwordEncoder.encode(cliente.getPassword());
         cliente.setPassword(encoder);
         clienteRepository.save(cliente);
         return cliente;
    }

    public List<ClienteEntity> listAll(){
        return clienteRepository.findAll();
    }

    public ClienteEntity listById(Long id){
        Optional<ClienteEntity> clienteFind = clienteRepository.findById(id);
        return clienteFind.orElseThrow(() -> new RuntimeException(
                "Cliente não encontrado"));
    }

    @Transactional
    public ClienteEntity update(ClienteEntity cliente, Long id){
        listById(id);
        String encoder = this.passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(encoder);
        return cliente;
    }

    public void delete(Long id){
        listById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel excluir pois não encontramos esse ID no nosso banco de dados!");
        }
    }

    public Boolean validarSenha(LoginRequest cliente){
        String senha = clienteRepository.findByLogin(cliente.getLogin()).get().getPassword();
        Boolean valid = passwordEncoder.matches(cliente.getPassword(), senha);
        return valid;
    }
}
