package com.example.libreria_miguelon.domain.libro;

import com.example.libreria_miguelon.domain.autor.Autor;
import com.example.libreria_miguelon.domain.autor.DatosAutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosLibro(
       @NotBlank String titulo,
        @NotBlank String genero,
        @NotNull DatosAutor autor,
        @NotNull int anioPublicacion,
         @NotBlank String editorial
) {

}
