package com.example.libreria_miguelon.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Usuario {
    private String correo;

    public Usuario(DatosUsuario datos){
        this.correo = datos.correo();
    }

    public void actualizarInformacion(DatosUsuario datos){
        if(datos.correo()!=null){
            this.correo = datos.correo();
        }
    }
}
