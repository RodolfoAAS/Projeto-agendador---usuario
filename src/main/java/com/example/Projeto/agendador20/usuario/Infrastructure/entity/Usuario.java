package com.example.Projeto.agendador20.usuario.Infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuario")
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", length = 100,nullable = false)
    private String nome;
    @Column(name = "email", length = 100,nullable = false)
    private String email;
    @Column(name = "senha",nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

}
