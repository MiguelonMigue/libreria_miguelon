package com.example.libreria_miguelon.user;

public record DatosUsuario(
        String correo

) {
    public DatosUsuario(Usuario usuario){
        this(usuario.getCorreo());
    }
}
