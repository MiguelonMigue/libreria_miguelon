package com.example.libreria_miguelon.domain.autor;

import com.example.libreria_miguelon.domain.libro.DatosLibro;
import com.example.libreria_miguelon.domain.libro.Libro;
import com.example.libreria_miguelon.user.DatosUsuario;
import com.example.libreria_miguelon.user.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAutor(String nombre, int nacimiento, DatosUsuario usuario) {
    public DatosAutor(Autor autor) {
        this(
                autor.getNombre(),
                autor.getNacimiento(),
                autor.getUsuario() != null ? new DatosUsuario(autor.getUsuario()) : new DatosUsuario("N/A")
        );
    }
}
