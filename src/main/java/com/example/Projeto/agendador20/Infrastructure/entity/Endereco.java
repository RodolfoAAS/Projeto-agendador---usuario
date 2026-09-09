package com.example.Projeto.agendador20.Infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Endereco")
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rua", length = 100,nullable = false)
    private String rua;
    @Column(name = "numero", length = 100,nullable = false)
    private String numero;
    @Column(name = "complemento", length = 10,nullable = true)
    private String complemento;
    @Column(name = "cidade", length = 150,nullable = false)
    private String cidade;
    @Column(name = "estado", length = 2,nullable = false)
    private String estado;
    @Column(name = "cep", length = 9,nullable = false)
    private String cep;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

}
