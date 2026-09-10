package com.example.Projeto.agendador20.usuario.Infrastructure.repository;

import com.example.Projeto.agendador20.usuario.Infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
