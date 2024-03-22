package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import com.apiempresausers.apiempresausers.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteEntity create(ClienteEntity cliente){
         clienteRepository.save(cliente);
         return cliente;
    }

    public List<ClienteEntity> listAll(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> listById(Long id){
        return clienteRepository.findById(id);
    }

    public String update(ClienteEntity cliente){
        clienteRepository.save(cliente);
        return "Cliente Atualizado";
    }
    public String delete(Long id){
        clienteRepository.deleteById(id);
        return "Cliente excluido";
    }
}
