package com.apiempresausers.apiempresausers.service;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import com.apiempresausers.apiempresausers.entity.PedidosEntity;
import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import com.apiempresausers.apiempresausers.entity.dto.GetPedidosDTO;
import com.apiempresausers.apiempresausers.entity.dto.SetPedidosDTO;
import com.apiempresausers.apiempresausers.entity.dto.SetProdutosDTO;
import com.apiempresausers.apiempresausers.repository.ClienteRepository;
import com.apiempresausers.apiempresausers.repository.PedidosRepository;
import com.apiempresausers.apiempresausers.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosService {
    private PedidosRepository pedidosRepository;
    private ProdutosRepository produtosRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public PedidosService(PedidosRepository pedidosRepository, ProdutosRepository produtosRepository, ClienteRepository clienteRepository) {
        this.pedidosRepository = pedidosRepository;
        this.produtosRepository = produtosRepository;
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public String comprarProduto(SetPedidosDTO produtoDTO) {
        ProdutosEntity produto = produtosRepository.findById(produtoDTO.getProduto_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoDTO.getProduto_id()));

        if (!produto.isOfferActive()) throw new RuntimeException("Esse produto não está a venda");

        ClienteEntity novoDonoProduto = clienteRepository.findById(produtoDTO.getNovo_dono_produto_id())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + produtoDTO.getNovo_dono_produto_id()));

        ClienteEntity antigoDonoProduto = clienteRepository.findById(produtoDTO.getAntigo_dono_produto_id())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + produtoDTO.getAntigo_dono_produto_id()));

        PedidosEntity pedido = new PedidosEntity();

        pedido.setAntigo_dono_produto_id(antigoDonoProduto);
        pedido.setProduto(produto);
        pedido.setNovo_dono_produto_id(novoDonoProduto);

        produto.setDono_produto_id(novoDonoProduto);
        produto.setOfferActive(false);

        pedidosRepository.save(pedido);

        return "Produto comprado";
    }

    public List<GetPedidosDTO> listarTransacoes() {
        List<PedidosEntity> pedidos = pedidosRepository.findAll();

        List<GetPedidosDTO> pedidosDTOList = new ArrayList<>();
        for (PedidosEntity pedido : pedidos) {
            GetPedidosDTO pedidoDTO = new GetPedidosDTO();

            //Informações sobre o pedido
            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setData_pedido(pedido.getData_pedido().getTime());

            //Informações sobre o antigo dono
            ClienteEntity antigoDonoDTO = new ClienteEntity();
            antigoDonoDTO.setId(pedido.getAntigo_dono_produto_id().getId());
            antigoDonoDTO.setLogin(pedido.getAntigo_dono_produto_id().getLogin());

            pedidoDTO.setAntigo_dono_produto(antigoDonoDTO);

            //Informações sobre o produto
            ProdutosEntity produtoDTO = new ProdutosEntity();
            produtoDTO.setId(pedido.getProduto().getId());
            produtoDTO.setNome_produto(pedido.getProduto().getNome_produto());
            produtoDTO.setValor_produto(pedido.getProduto().getValor_produto());

            pedidoDTO.setProduto(produtoDTO);

            //Informações sobre o novo dono
            ClienteEntity novoDonoDTO = new ClienteEntity();
            novoDonoDTO.setId(pedido.getNovo_dono_produto_id().getId());
            novoDonoDTO.setLogin(pedido.getNovo_dono_produto_id().getLogin());

            pedidoDTO.setNovo_dono_produto(novoDonoDTO);

            pedidosDTOList.add(pedidoDTO);
        }

        return pedidosDTOList;
    }
}