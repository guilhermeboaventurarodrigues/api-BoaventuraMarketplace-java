package com.apiBoaventuraMarketplace;

import com.apiBoaventuraMarketplace.controller.ClienteController;
import com.apiBoaventuraMarketplace.controller.ProdutosController;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import com.apiBoaventuraMarketplace.entity.TransacoesEntity;
import com.apiBoaventuraMarketplace.entity.dto.SetProdutosDTO;
import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.repository.ProdutosRepository;
import com.apiBoaventuraMarketplace.repository.TransacoesRepository;
import com.apiBoaventuraMarketplace.service.ClienteService;
import com.apiBoaventuraMarketplace.service.ProdutosService;
import com.apiBoaventuraMarketplace.service.UserDetailsImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarClientesTeste {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteController clienteController;
    ClienteEntity clienteEntity = new ClienteEntity();

    @BeforeEach
    public void setup() {
        clienteEntity.setNome("Guilherme");
        clienteEntity.setCpf("12345678910");
        clienteEntity.setDataDeNascimento("2000-10-01");
        clienteEntity.setLogin("guilherme");
        clienteEntity.setPassword("123456");
        clienteEntity.setSegmentoCliente(SegmentoClienteEnum.COMPRADOR);
    }

    @Test
    @Order(1)
    @DisplayName("Testando repository de criação de clientes")
    void criarClienteRepository() {
        ClienteEntity clienteSalved = clienteRepository.save(clienteEntity);
        assertThat(clienteSalved).isNotNull();
        assertThat(clienteSalved.getId()).isNotNull();
        assertThat(clienteSalved.getLogin()).isEqualTo(clienteEntity.getLogin());
        assertThat(clienteSalved.getPassword()).isEqualTo(clienteEntity.getPassword());
        assertThat(clienteSalved.getSegmentoCliente()).isEqualTo(clienteEntity.getSegmentoCliente());
        assertThat(clienteSalved.getCpf()).isEqualTo(clienteEntity.getCpf());
    }

    @Test
    @Order(2)
    @DisplayName("Testando serviço de criação de clientes")
    void criarClienteService() {
        ClienteEntity clienteSaved = clienteService.create(clienteEntity);
        assertThat(clienteSaved).isNotNull();
        assertThat(clienteSaved.getId()).isNotNull();
        assertThat(clienteSaved.getLogin()).isEqualTo(clienteEntity.getLogin());
        assertThat(clienteSaved.getPassword()).isEqualTo(clienteEntity.getPassword());
        assertThat(clienteSaved.getSegmentoCliente()).isEqualTo(clienteEntity.getSegmentoCliente());
        assertThat(clienteSaved.getCpf()).isEqualTo(clienteEntity.getCpf());
    }

    @Test
    @Order(3)
    @DisplayName("Testando controller de criação de clientes")
    void criarClienteController() {
        ClienteEntity clienteSaved = clienteService.create(clienteEntity);
        ResponseEntity<ClienteEntity> http = clienteController.create(clienteSaved);
        assertThat(http).isNotNull();
        assertThat(http.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(http.getBody()).isNotNull();
        assertThat(http.getBody().getLogin()).isEqualTo(clienteSaved.getLogin());
    }
}

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarProdutoTeste {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ProdutosService produtosService;
    @Autowired
    private ProdutosController produtosController;
    ClienteEntity clienteEntity = new ClienteEntity();

    @BeforeEach
    public void setup() {
        clienteEntity.setNome("Guilherme");
        clienteEntity.setCpf("12345678910");
        clienteEntity.setDataDeNascimento("2000-10-01");
        clienteEntity.setLogin("guilherme");
        clienteEntity.setPassword("123456");
        clienteEntity.setSegmentoCliente(SegmentoClienteEnum.COMPRADOR);
    }

    @Test
    @Order(1)
    @DisplayName("Testando repository de criação de produtos")
    void criarProdutoRepository() {
        ClienteEntity clienteSalved = clienteRepository.save(clienteEntity);

        ProdutosEntity produtosEntity = new ProdutosEntity();
        produtosEntity.setNome_produto("Computador");
        produtosEntity.setDescricao_produto("Computador descrição");
        produtosEntity.setValor_produto(1000.00);
        produtosEntity.setOfferActive(true);
        produtosEntity.setDono_produto_id(clienteSalved);

        System.out.println("produtosEntity: " + produtosEntity);
        System.out.println("clienteEntity: " + clienteSalved);
        ProdutosEntity produtosRepositorySaved = produtosRepository.save(produtosEntity);

        assertThat(produtosRepositorySaved).isNotNull();
        assertThat(produtosRepositorySaved.getId()).isNotNull();
        assertThat(produtosRepositorySaved.getNome_produto()).isEqualTo(produtosEntity.getNome_produto());
        assertThat(produtosRepositorySaved.getDescricao_produto()).isEqualTo(produtosEntity.getDescricao_produto());
        assertThat(produtosRepositorySaved.getValor_produto()).isEqualTo(produtosEntity.getValor_produto());
        assertThat(produtosRepositorySaved.isOfferActive()).isEqualTo(produtosEntity.isOfferActive());
        assertThat(produtosRepositorySaved.getDono_produto_id()).isEqualTo(produtosEntity.getDono_produto_id());
        assertThat(produtosRepositorySaved.getDono_produto_id().getLogin()).isEqualTo(clienteSalved.getLogin());
        assertThat(produtosRepositorySaved.getDono_produto_id().getSegmentoCliente()).isEqualTo(clienteSalved.getSegmentoCliente());
    }

    @Test
    @Order(2)
    @DisplayName("Testando serviço de criação de produtos")
    void criarProdutoService() {
        ClienteEntity clienteSalved = clienteRepository.save(clienteEntity);

        // Simulando o UserDetails do usuário autenticado
        UserDetailsImpl userDetails = UserDetailsImpl.build(clienteSalved); // Criando uma instância UserDetailsImpl com o cliente criado

        // Simulando a autenticação do usuário
        UsernamePasswordAuthenticationToken auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(auth.getPrincipal()).thenReturn(userDetails);

        // Configurando o contexto de segurança com a autenticação simulada
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Restante do seu teste
        ProdutosEntity produtosEntity = new ProdutosEntity();
        produtosEntity.setNome_produto("Computador");
        produtosEntity.setDescricao_produto("Computador descrição");
        produtosEntity.setValor_produto(1000.00);
        produtosEntity.setOfferActive(true);
        produtosEntity.setDono_produto_id(clienteSalved);

        SetProdutosDTO produtosDTO = produtosService.convertEntityTODTO(produtosEntity);

        ProdutosEntity produtosSaved = produtosService.create(produtosDTO);

        assertThat(produtosSaved).isNotNull();
        assertThat(produtosSaved.getId()).isNotNull();
        assertThat(produtosSaved.getNome_produto()).isEqualTo(produtosEntity.getNome_produto());
        assertThat(produtosSaved.getDescricao_produto()).isEqualTo(produtosEntity.getDescricao_produto());
        assertThat(produtosSaved.getValor_produto()).isEqualTo(produtosEntity.getValor_produto());
        assertThat(produtosSaved.isOfferActive()).isEqualTo(produtosEntity.isOfferActive());
        assertThat(produtosSaved.getDono_produto_id().getId()).isEqualTo(produtosEntity.getDono_produto_id().getId());
        assertThat(produtosSaved.getDono_produto_id().getLogin()).isEqualTo(clienteSalved.getLogin());
        assertThat(produtosSaved.getDono_produto_id().getSegmentoCliente()).isEqualTo(clienteSalved.getSegmentoCliente());
    }

    @Test
    @Order(3)
    @DisplayName("Testando controller de criação de produtos")
    void criarProdutoController() {
        ClienteEntity clienteSalved = clienteRepository.save(clienteEntity);

        // Simulando o UserDetails do usuário autenticado
        UserDetailsImpl userDetails = UserDetailsImpl.build(clienteSalved); // Criando uma instância UserDetailsImpl com o cliente criado

        // Simulando a autenticação do usuário
        UsernamePasswordAuthenticationToken auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(auth.getPrincipal()).thenReturn(userDetails);

        // Configurando o contexto de segurança com a autenticação simulada
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Restante do seu teste
        ProdutosEntity produtosEntity = new ProdutosEntity();
        produtosEntity.setNome_produto("Computador");
        produtosEntity.setDescricao_produto("Computador descrição");
        produtosEntity.setValor_produto(1000.00);
        produtosEntity.setOfferActive(true);
        produtosEntity.setDono_produto_id(clienteSalved);

        ResponseEntity<ProdutosEntity> http = produtosController.create(produtosService.convertEntityTODTO(produtosEntity));
        assertThat(http).isNotNull();
        assertThat(http.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(http.getBody()).isNotNull();
        assertThat(http.getBody().getNome_produto()).isEqualTo(produtosEntity.getNome_produto());
    }
}

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarTransacoesTeste {
    @Autowired
    private TransacoesRepository transacoesRepository;

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
}