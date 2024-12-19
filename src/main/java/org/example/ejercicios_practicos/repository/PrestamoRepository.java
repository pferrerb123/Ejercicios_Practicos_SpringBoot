package org.example.ejercicios_practicos.repository;


import org.example.ejercicios_practicos.model.Prestamo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PrestamoRepository extends ReactiveCrudRepository<Prestamo, String> {}