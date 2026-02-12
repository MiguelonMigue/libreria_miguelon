package com.example.libreria_miguelon.domain.libro;

import com.example.libreria_miguelon.domain.autor.DatosAutor;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionLibro(
       @NotNull Long id,
       String titulo,
       String genero,
       DatosAutor autor,
       int anioPublicacion,
       String editorial

) {
}
