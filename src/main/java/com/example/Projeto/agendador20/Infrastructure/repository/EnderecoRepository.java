package com.example.Projeto.agendador20.Infrastructure.repository;

import com.example.Projeto.agendador20.Infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
