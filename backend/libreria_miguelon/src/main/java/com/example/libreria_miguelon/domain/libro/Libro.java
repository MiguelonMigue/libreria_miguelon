package com.example.libreria_miguelon.domain.libro;

import com.example.libreria_miguelon.domain.autor.Autor;
import com.example.libreria_miguelon.domain.autor.DatosAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
@Entity(name = "Libro")
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    @Embedded
    private Autor autor;
    private Integer anioPublicacion;
    private String editorial;

    public Libro(DatosLibro datos){
        this.id = null;
        this.titulo = datos.titulo();
        this.genero = datos.genero();
        this.autor = new Autor(datos.autor());
        this.anioPublicacion = datos.anioPublicacion();
        this.editorial = datos.editorial();
    }

    public void actualizarInformacion(DatosActualizacionLibro datos){
        if(datos.titulo()!=null){
            this.titulo = datos.titulo();
        }
        if(datos.genero()!=null){
            this.genero = datos.genero();
        }
    }
}
