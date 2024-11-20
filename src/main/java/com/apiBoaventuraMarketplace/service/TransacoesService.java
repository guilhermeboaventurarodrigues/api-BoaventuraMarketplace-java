package com.apiBoaventuraMarketplace.service;

import com.apiBoaventuraMarketplace.entity.dto.GetPedidosDTO;
import com.apiBoaventuraMarketplace.entity.dto.SetPedidosDTO;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.repository.ProdutosRepository;
import com.apiBoaventuraMarketplace.repository.TransacoesRepository;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.TransacoesEntity;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacoesService {
    private TransacoesRepository transacoesRepository;
    private ProdutosRepository produtosRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public TransacoesService(TransacoesRepository transacoesRepository, ProdutosRepository produtosRepository, ClienteRepository clienteRepository) {
        this.transacoesRepository = transacoesRepository;
        this.produtosRepository = produtosRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public String comprarProduto(SetPedidosDTO produtoDTO) {
        ProdutosEntity produto = produtosRepository.findById(produtoDTO.getProduto_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoDTO.getProduto_id()));

        if (!produto.isOfferActive()) throw new RuntimeException("Esse produto não está a venda");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userLogin = authentication.getName();

        ClienteEntity novoDonoProduto = clienteRepository.findByLogin(userLogin)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: "));

        ClienteEntity antigoDonoProduto = clienteRepository.findById(produto.getDono_produto_id().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + produtoDTO.getAntigo_dono_produto_id()));

        TransacoesEntity pedido = new TransacoesEntity();

        pedido.setAntigo_dono_produto_id(antigoDonoProduto);
        pedido.setProduto(produto);
        pedido.setNovo_dono_produto_id(novoDonoProduto);

        produto.setDono_produto_id(novoDonoProduto);
        produto.setOfferActive(false);

        transacoesRepository.save(pedido);

        return "Produto comprado";
    }

    public List<GetPedidosDTO> listarTransacoes() {
        List<TransacoesEntity> pedidos = transacoesRepository.findAll();

        List<GetPedidosDTO> pedidosDTOList = new ArrayList<>();
        for (TransacoesEntity pedido : pedidos) {
            GetPedidosDTO pedidoDTO = new GetPedidosDTO();

            //Informações sobre o pedido
            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setData_pedido(pedido.getData_pedido().getTime());

            //Informações sobre o antigo dono
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setId(pedido.getAntigo_dono_produto_id().getId());
            clienteEntity.setLogin(pedido.getAntigo_dono_produto_id().getLogin());

            pedidoDTO.setAntigo_dono_produto(clienteEntity);

            //Informações sobre o produto
            ProdutosEntity produtosEntity = new ProdutosEntity();
            produtosEntity.setId(pedido.getProduto().getId());
            produtosEntity.setNome_produto(pedido.getProduto().getNome_produto());
            produtosEntity.setValor_produto(pedido.getProduto().getValor_produto());

            pedidoDTO.setProduto(produtosEntity);

            //Informações sobre o novo dono
            ClienteEntity clienteEntity1 = new ClienteEntity();
            clienteEntity1.setId(pedido.getNovo_dono_produto_id().getId());
            clienteEntity1.setLogin(pedido.getNovo_dono_produto_id().getLogin());

            pedidoDTO.setNovo_dono_produto(clienteEntity1);

            pedidosDTOList.add(pedidoDTO);
        }

        return pedidosDTOList;
    }
}