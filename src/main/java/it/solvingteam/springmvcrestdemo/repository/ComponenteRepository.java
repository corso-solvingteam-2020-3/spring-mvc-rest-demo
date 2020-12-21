package it.solvingteam.springmvcrestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.springmvcrestdemo.model.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, Integer> {

}
