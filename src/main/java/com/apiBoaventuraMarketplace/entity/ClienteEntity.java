package com.apiBoaventuraMarketplace.entity;

import com.apiBoaventuraMarketplace.entity.enums.SegmentoClienteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ClienteEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String dataDeNascimento;

    @NotNull
    @NotEmpty
    private String cpf;

    private SegmentoClienteEnum segmentoCliente;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "dono_produto_id")
    private List<ProdutosEntity> meus_produtos = new ArrayList<ProdutosEntity>();
}
