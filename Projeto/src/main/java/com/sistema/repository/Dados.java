package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.model.Informacao;

public interface Dados extends JpaRepository<Informacao, Long>{

}
