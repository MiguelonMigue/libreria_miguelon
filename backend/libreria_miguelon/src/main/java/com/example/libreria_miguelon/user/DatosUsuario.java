package com.example.libreria_miguelon.user;

public record DatosUsuario(String correo) {
    public DatosUsuario(Usuario usuario) {
        // Si el usuario es nulo, ponemos un texto por defecto o null
        this(usuario != null ? usuario.getCorreo() : "Sin correo");
    }
}