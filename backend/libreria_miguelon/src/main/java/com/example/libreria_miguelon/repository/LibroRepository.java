package com.example.libreria_miguelon.repository;

import com.example.libreria_miguelon.domain.libro.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {

}
