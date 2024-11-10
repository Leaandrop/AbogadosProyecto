package com.mi_abogados.models;

import java.sql.Timestamp;

public class Documento {
    private int id;
    private String nombreDocumento;
    private String tipoDocumento;
    private Timestamp fechaSubida;
    private int idCaso;

    public Documento(int id, String nombreDocumento, String tipoDocumento, Timestamp fechaSubida, int idCaso) {
        this.id = id;
        this.nombreDocumento = nombreDocumento;
        this.tipoDocumento = tipoDocumento;
        this.fechaSubida = fechaSubida;
        this.idCaso = idCaso;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Timestamp getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Timestamp fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }
}
