package org.example.ejercicios_practicos.repository;


import org.example.ejercicios_practicos.model.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClienteRepository extends ReactiveCrudRepository<Cliente, String> {}