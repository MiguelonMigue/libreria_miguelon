package com.example.libreria_miguelon.controller;

import com.example.libreria_miguelon.domain.autor.Autor;
import com.example.libreria_miguelon.domain.autor.DatosAutor;
import com.example.libreria_miguelon.domain.libro.DatosActualizacionLibro;
import com.example.libreria_miguelon.domain.libro.DatosLibro;
import com.example.libreria_miguelon.domain.libro.DatosListaLibro;
import com.example.libreria_miguelon.domain.libro.Libro;

import com.example.libreria_miguelon.repository.LibroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/libro")

public class LibroController {

    @Autowired
    private LibroRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosLibro datos){
        repository.save(new Libro(datos));
    }

    @GetMapping
    public Page<DatosListaLibro>listar(@PageableDefault Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaLibro::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaLibro>detallar(@PathVariable Long id){
        var libro = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));
        DatosListaLibro datos = new DatosListaLibro(libro);
        return ResponseEntity.ok(datos);
    }

    @PutMapping
    public void actualizar (@RequestBody @Valid DatosActualizacionLibro datos){
        var libro = repository.getReferenceById(datos.id());
        libro.actualizarInformacion(datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        repository.deleteById(id);
    }

}
