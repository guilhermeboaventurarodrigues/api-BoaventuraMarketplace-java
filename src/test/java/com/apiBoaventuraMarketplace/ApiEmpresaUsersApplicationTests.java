package com.apiBoaventuraMarketplace;

import com.apiBoaventuraMarketplace.controller.ClienteController;
import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarClientesTeste {
	@Autowired
	private ClienteRepository  clienteRepository;

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
