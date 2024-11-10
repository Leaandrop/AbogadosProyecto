package com.mi_abogados.Dao;

import com.mi_abogados.ConexionDB;
import com.mi_abogados.models.Documento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO {

    // Método para obtener todos los documentos
    public List<Documento> obtenerTodos() throws SQLException {
        List<Documento> documentos = new ArrayList<>();
        String sql = "SELECT * FROM documentos";
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Documento documento = new Documento(
                    rs.getInt("id"),
                    rs.getString("nombre_documento"),
                    rs.getString("tipo_documento"),
                    rs.getTimestamp("fecha_subida"),
                    rs.getInt("id_caso")
                );
                documentos.add(documento);
            }
        }
        return documentos;
    }

    // Método para agregar un documento
    public void agregarDocumento(Documento documento) throws SQLException {
        String sql = "INSERT INTO documentos (nombre_documento, tipo_documento, fecha_subida, id_caso) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento.getNombreDocumento());
            stmt.setString(2, documento.getTipoDocumento());
            stmt.setTimestamp(3, documento.getFechaSubida());
            stmt.setInt(4, documento.getIdCaso());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar un documento existente
    public void actualizarDocumento(Documento documento) throws SQLException {
        String sql = "UPDATE documentos SET nombre_documento = ?, tipo_documento = ?, fecha_subida = ?, id_caso = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento.getNombreDocumento());
            stmt.setString(2, documento.getTipoDocumento());
            stmt.setTimestamp(3, documento.getFechaSubida());
            stmt.setInt(4, documento.getIdCaso());
            stmt.setInt(5, documento.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un documento
    public void eliminarDocumento(int id) throws SQLException {
        String sql = "DELETE FROM documentos WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Documento obtenerPorId(int id) throws SQLException {
        Documento documento = null;
        String sql = "SELECT * FROM documentos WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    documento = new Documento(
                        rs.getInt("id"),
                        rs.getString("nombre_documento"),
                        rs.getString("tipo_documento"),
                        rs.getTimestamp("fecha_subida"),
                        rs.getInt("id_caso")
                    );
                }
            }
        }
        return documento;
    }
}
