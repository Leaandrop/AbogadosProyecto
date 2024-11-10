package com.mi_abogados.models;

import java.util.Date;

public class Usuario {
    private int id;
    private String email;
    private String password;
    private String nombre;
    private String rol;
    private Date fechaRegistro;

    public Usuario(int id, String email, String password, String nombre, String rol, Date fechaRegistro) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
