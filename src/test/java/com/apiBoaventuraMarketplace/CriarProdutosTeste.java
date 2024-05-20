package com.apiBoaventuraMarketplace;

import com.apiBoaventuraMarketplace.controller.ProdutosController;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import com.apiBoaventuraMarketplace.entity.dto.SetProdutosDTO;
import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.repository.ProdutosRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CriarProdutosTeste {
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
        UserDetailsImpl userDetails = UserDetailsImpl.build(clienteSalved);

        // Simulando a autenticação do usuário
        UsernamePasswordAuthenticationToken auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(auth.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Criando um SetProdutosDTO com os dados do produto
        SetProdutosDTO produtosDTO = new SetProdutosDTO();
        produtosDTO.setNome_produto("Computador");
        produtosDTO.setDescricao_produto("Computador descrição");
        produtosDTO.setValor_produto(1000.00);
        produtosDTO.setOfferActive(true);

        // Chamando o método create do serviço
        ProdutosEntity produtosSaved = produtosService.create(produtosDTO);

        assertThat(produtosSaved).isNotNull();
        assertThat(produtosSaved.getId()).isNotNull();
        assertThat(produtosSaved.getNome_produto()).isEqualTo("Computador");
        assertThat(produtosSaved.getDescricao_produto()).isEqualTo("Computador descrição");
        assertThat(produtosSaved.getValor_produto()).isEqualTo(1000.00);
        assertThat(produtosSaved.isOfferActive()).isTrue();
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

        SetProdutosDTO produtosDTO = new SetProdutosDTO();
        produtosDTO.setNome_produto("Computador");
        produtosDTO.setDescricao_produto("Computador descrição");
        produtosDTO.setValor_produto(1000.00);
        produtosDTO.setOfferActive(true);

        ResponseEntity<ProdutosEntity> http = produtosController.create(produtosDTO);
        assertThat(http).isNotNull();
        assertThat(http.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(http.getBody()).isNotNull();
    }
}

