package com.example.libreria_miguelon.domain.autor;

import com.example.libreria_miguelon.domain.libro.Libro;
import com.example.libreria_miguelon.user.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter


public class Autor {
    private String nombre;
    private int nacimiento;
    private Usuario usuario;


    public Autor(DatosAutor datos) {
        this.nombre = datos.nombre();
        this.nacimiento = datos.nacimiento();
        this.usuario = new Usuario(datos.usuario());

    }


}
