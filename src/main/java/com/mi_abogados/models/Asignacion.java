package com.mi_abogados.models;

import java.sql.Timestamp;

public class Asignacion {
    private int id;
    private int idCaso;
    private int idUsuario;
    private String rolAsignado;
    private Timestamp fechaAsignacion;

    public Asignacion(int id, int idCaso, int idUsuario, String rolAsignado, Timestamp fechaAsignacion) {
        this.id = id;
        this.idCaso = idCaso;
        this.idUsuario = idUsuario;
        this.rolAsignado = rolAsignado;
        this.fechaAsignacion = fechaAsignacion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRolAsignado() {
        return rolAsignado;
    }

    public void setRolAsignado(String rolAsignado) {
        this.rolAsignado = rolAsignado;
    }

    public Timestamp getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Timestamp fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
