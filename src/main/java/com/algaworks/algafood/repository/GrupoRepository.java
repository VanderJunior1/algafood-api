package com.algaworks.algafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
