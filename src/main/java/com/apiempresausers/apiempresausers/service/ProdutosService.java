package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import com.apiempresausers.apiempresausers.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ClienteService clienteService;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @Transactional
    public ProdutosEntity create(ProdutosEntity prod) {
        ClienteEntity donoProduto = clienteService.listById(prod.getDono_produto_id().getId());
        prod.setDono_produto_id(donoProduto);
        return this.produtosRepository.save(prod);
    }


    public List<ProdutosEntity> listAll() {
        return produtosRepository.findAll();
    }

    public ProdutosEntity listById(Long id) {
        Optional<ProdutosEntity> listed = produtosRepository.findById(id);
        return listed.orElseThrow(() -> new RuntimeException(
                "ID de produto não encontrado"
        ));
    }

    @Transactional
    public ProdutosEntity update(ProdutosEntity prod, Long id) {
        listById(id);
        prod.setNome_produto(prod.getNome_produto());
        prod.setDescricao_produto(prod.getNome_produto());
        prod.setValor_produto(prod.getValor_produto());
        return prod;
    }

    public void delete(Long id) {
        listById(id);
        try {
            produtosRepository.deleteById(id);
        }catch (RuntimeException e) {
            throw new RuntimeException("Produto não encontrado!");
        }

    }
}
