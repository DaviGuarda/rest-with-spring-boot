package br.com.davidev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davidev.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}