package com.apiBoaventuraMarketplace;

import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CriarClienteTeste {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();
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
    @DisplayName("Testando POST de Clientes - 201 CREATED")
    void criarClienteController() throws Exception {
        String clienteJson = objectMapper.writeValueAsString(clienteEntity);

        var response = mvc.perform(post("/clientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}