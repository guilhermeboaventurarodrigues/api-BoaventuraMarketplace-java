package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import com.apiempresausers.apiempresausers.entity.dto.GetProdutosDTO;
import com.apiempresausers.apiempresausers.entity.dto.SetProdutosDTO;
import com.apiempresausers.apiempresausers.entity.dto.UpdateProdutosDTO;
import com.apiempresausers.apiempresausers.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public ProdutosEntity create(SetProdutosDTO prod) {
        ProdutosEntity produtosEntity = new ProdutosEntity();
        produtosEntity.setNome_produto(prod.getNome_produto());
        produtosEntity.setDescricao_produto(prod.getDescricao_produto());
        produtosEntity.setValor_produto(prod.getValor_produto());
        produtosEntity.setOfferActive(prod.isOfferActive());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        ClienteEntity donoProduto = clienteService.listById(userId);
        produtosEntity.setDono_produto_id(donoProduto);

        return this.produtosRepository.save(produtosEntity);
    }


    public List<GetProdutosDTO> listAll() {
        List<ProdutosEntity> produtosEntities = produtosRepository.findAll();

        List<GetProdutosDTO> produtosDTOList = new ArrayList<>();

        for (ProdutosEntity produtoEntity1 : produtosEntities) {
            GetProdutosDTO produtoDTO = new GetProdutosDTO(produtoEntity1);
            produtosDTOList.add(produtoDTO);
        }

        return produtosDTOList;
    }

    public GetProdutosDTO listById(Long id) {
        ProdutosEntity listed = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID de produto não encontrado"));
        return new GetProdutosDTO(listed);
    }

    @Transactional
    public ProdutosEntity update(UpdateProdutosDTO prod, Long id) {
        ProdutosEntity newObj = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID de produto não encontrado"));
        newObj.setNome_produto(prod.getNome_produto());
        newObj.setDescricao_produto(prod.getNome_produto());
        newObj.setValor_produto(prod.getValor_produto());
        return newObj;
    }

    public void delete(Long id) {
        listById(id);
        try {
            produtosRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Produto não encontrado!");
        }

    }

    @Transactional
    public GetProdutosDTO anunciarProdutos(SetProdutosDTO produto, Long id){
        ProdutosEntity newObj = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID de produto não encontrado"));
        newObj.setOfferActive(produto.isOfferActive());
        produtosRepository.save(newObj);
        return new GetProdutosDTO(newObj);
    }
}
