package com.apiBoaventuraMarketplace;

import com.apiBoaventuraMarketplace.controller.TransacoesController;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import com.apiBoaventuraMarketplace.entity.TransacoesEntity;
import com.apiBoaventuraMarketplace.entity.dto.SetPedidosDTO;
import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.repository.ProdutosRepository;
import com.apiBoaventuraMarketplace.repository.TransacoesRepository;
import com.apiBoaventuraMarketplace.service.TransacoesService;
import com.apiBoaventuraMarketplace.service.UserDetailsImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CriarTransacoesTeste {
    @Autowired
    private TransacoesRepository transacoesRepository;

    @Autowired
    private TransacoesService transacoesService;

    @Autowired
    private TransacoesController transacoesController;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    ClienteEntity clienteEntity1 = new ClienteEntity();
    ClienteEntity clienteEntity2 = new ClienteEntity();
    ProdutosEntity produtosEntity = new ProdutosEntity();

    @BeforeEach
    public void setup() {
        clienteEntity1.setNome("Guilherme");
        clienteEntity1.setCpf("12345678910");
        clienteEntity1.setDataDeNascimento("2000-10-01");
        clienteEntity1.setLogin("guilherme");
        clienteEntity1.setPassword("123456");
        clienteEntity1.setSegmentoCliente(SegmentoClienteEnum.COMPRADOR);

        clienteEntity2.setNome("Ana");
        clienteEntity2.setCpf("12345678910");
        clienteEntity2.setDataDeNascimento("2000-10-01");
        clienteEntity2.setLogin("ana");
        clienteEntity2.setPassword("123456");
        clienteEntity2.setSegmentoCliente(SegmentoClienteEnum.VENDEDOR);

        produtosEntity.setNome_produto("Computador");
        produtosEntity.setDescricao_produto("Computador descrição");
        produtosEntity.setValor_produto(1000.00);
        produtosEntity.setOfferActive(true);
    }

    @Test
    @Order(1)
    @DisplayName("Testando repository de criação de transações")
    void criarTransacoesRepository() {
        ClienteEntity clienteEntitySaved1 = clienteRepository.save(clienteEntity1);
        ClienteEntity clienteEntitySaved2 = clienteRepository.save(clienteEntity2);
        produtosEntity.setDono_produto_id(clienteEntitySaved1);
        ProdutosEntity produtosEntitySaved1 = produtosRepository.save(produtosEntity);

        TransacoesEntity transacoesEntity = new TransacoesEntity();
        transacoesEntity.setAntigo_dono_produto_id(clienteEntitySaved1);
        transacoesEntity.setProduto(produtosEntitySaved1);
        transacoesEntity.setNovo_dono_produto_id(clienteEntitySaved2);
        transacoesEntity.setData_pedido(new Date());
        TransacoesEntity transacoesSaved = transacoesRepository.save(transacoesEntity);
        System.out.println(transacoesSaved);
        assertThat(transacoesSaved).isNotNull();
        assertThat(transacoesSaved.getId()).isNotNull();
        assertThat(transacoesSaved.getAntigo_dono_produto_id().getLogin()).isEqualTo(clienteEntitySaved1.getLogin());
        assertThat(transacoesSaved.getNovo_dono_produto_id().getLogin()).isEqualTo(clienteEntitySaved2.getLogin());
        assertThat(transacoesSaved.getProduto().getNome_produto()).isEqualTo(produtosEntitySaved1.getNome_produto());
        assertThat(transacoesSaved.getData_pedido()).isEqualTo(transacoesEntity.getData_pedido());
    }

    @Test
    @Order(2)
    @DisplayName("Testando service de criação de transações")
    void criarTransacoesService() {
        ClienteEntity clienteEntitySaved1 = clienteRepository.save(clienteEntity1);
        ClienteEntity clienteEntitySaved2 = clienteRepository.save(clienteEntity2);
        produtosEntity.setDono_produto_id(clienteEntitySaved1);
        ProdutosEntity produtosEntitySaved1 = produtosRepository.save(produtosEntity);

        // Simulando o UserDetails do usuário autenticado
        UserDetailsImpl userDetails = UserDetailsImpl.build(clienteEntitySaved2); // Criando uma instância UserDetailsImpl com o cliente criado

        // Simulando a autenticação do usuário
        UsernamePasswordAuthenticationToken auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(auth.getPrincipal()).thenReturn(userDetails);

        // Configurando o contexto de segurança com a autenticação simulada
        SecurityContextHolder.getContext().setAuthentication(auth);

        SetPedidosDTO transacoesEntity = new SetPedidosDTO();
        transacoesEntity.setAntigo_dono_produto_id(clienteEntitySaved1.getId());
        transacoesEntity.setProduto_id(produtosEntitySaved1.getId());
        transacoesEntity.setNovo_dono_produto_id(clienteEntitySaved2.getId());

        String transacoesSaved = transacoesService.comprarProduto(transacoesEntity);

        assertThat(transacoesSaved).isNotNull();
        assertThat(transacoesSaved).isEqualTo("Produto comprado");
    }

    @Test
    @Order(3)
    @DisplayName("Testando controller de criação de transações")
    void criarTransacoesController() {
        ClienteEntity clienteEntitySaved1 = clienteRepository.save(clienteEntity1);
        ClienteEntity clienteEntitySaved2 = clienteRepository.save(clienteEntity2);
        produtosEntity.setDono_produto_id(clienteEntitySaved1);
        ProdutosEntity produtosEntitySaved1 = produtosRepository.save(produtosEntity);

        // Simulando o UserDetails do usuário autenticado
        UserDetailsImpl userDetails = UserDetailsImpl.build(clienteEntitySaved2); // Criando uma instância UserDetailsImpl com o cliente criado

        // Simulando a autenticação do usuário
        UsernamePasswordAuthenticationToken auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(auth.getPrincipal()).thenReturn(userDetails);

        // Configurando o contexto de segurança com a autenticação simulada
        SecurityContextHolder.getContext().setAuthentication(auth);

        SetPedidosDTO transacoesEntity = new SetPedidosDTO();
        transacoesEntity.setAntigo_dono_produto_id(clienteEntitySaved1.getId());
        transacoesEntity.setProduto_id(produtosEntitySaved1.getId());
        transacoesEntity.setNovo_dono_produto_id(clienteEntitySaved2.getId());

        ResponseEntity<String> http = transacoesController.comprarProduto(transacoesEntity);

        assertThat(http).isNotNull();
        assertThat(http.getBody()).isEqualTo("Produto comprado");
        assertThat(http.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(http.getHeaders()).isNotNull();
    }
}