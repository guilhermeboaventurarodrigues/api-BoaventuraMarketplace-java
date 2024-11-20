package com.apiBoaventuraMarketplace.controller;

import com.apiBoaventuraMarketplace.entity.ClienteEntity;
import com.apiBoaventuraMarketplace.entity.dto.AuthResponse;
import com.apiBoaventuraMarketplace.entity.dto.LoginRequest;
import com.apiBoaventuraMarketplace.repository.ClienteRepository;
import com.apiBoaventuraMarketplace.service.ClienteService;
import com.apiBoaventuraMarketplace.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest cliente) {
        // Validar senha
        Boolean senhaValida = clienteService.validarSenha(cliente);

        if (!senhaValida) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "Credenciais inválidas", false));
        }

        // Buscar o cliente completo pelo ID
        ClienteEntity clienteAutenticado = clienteRepository.findByLogin(cliente.getLogin())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Gerar token JWT
        UserDetails userDetails = User.builder()
                .username(clienteAutenticado.getLogin())
                .password(clienteAutenticado.getPassword())
                .roles("CLIENTE")
                .build();

        String token = jwtService.generateToken(userDetails);

        // Retornar resposta de sucesso com token
        return ResponseEntity.ok(
                new AuthResponse(token, "Login realizado com sucesso", true)
        );
    }
}