package it.solvingteam.springmvcrestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.springmvcrestdemo.model.Pc;

public interface PcRepository extends JpaRepository<Pc, Integer> {

}
