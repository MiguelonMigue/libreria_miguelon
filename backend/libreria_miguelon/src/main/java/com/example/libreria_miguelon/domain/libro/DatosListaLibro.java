package com.example.libreria_miguelon.domain.libro;

import com.example.libreria_miguelon.domain.autor.DatosAutor;

public record DatosListaLibro(
        Long id,
        String titulo,
        String genero,
        DatosAutor autor,
        String editorial,
        int anio_publicacion
) {
    public DatosListaLibro(Libro libro){
        this(libro.getId(), libro.getTitulo(), libro.getGenero(),new DatosAutor(libro.getAutor()), libro.getEditorial(), libro.getAnioPublicacion());
    }
}
