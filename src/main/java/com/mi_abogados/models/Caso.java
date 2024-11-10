package com.mi_abogados.models;

import java.sql.Date;

public class Caso {
    private int id;
    private String nombreCaso;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private int idAbogado;

    public Caso(int id, String nombreCaso, String descripcion, Date fechaInicio, Date fechaFin, String estado, int idAbogado) {
        this.id = id;
        this.nombreCaso = nombreCaso;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idAbogado = idAbogado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(int idAbogado) {
        this.idAbogado = idAbogado;
    }
}
