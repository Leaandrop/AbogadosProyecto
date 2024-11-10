package com.mi_abogados.Dao;

import com.mi_abogados.ConexionDB;
import com.mi_abogados.models.Caso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CasoDAO {

    // Método para obtener todos los casos
    public List<Caso> obtenerTodos() throws SQLException {
        List<Caso> casos = new ArrayList<>();
        String sql = "SELECT * FROM casos";
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Caso caso = new Caso(
                    rs.getInt("id"),
                    rs.getString("nombre_caso"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado"),
                    rs.getInt("id_abogado")
                );
                casos.add(caso);
            }
        }
        return casos;
    }

    // Método para agregar un caso
    public void agregarCaso(Caso caso) throws SQLException {
        String sql = "INSERT INTO casos (nombre_caso, descripcion, fecha_inicio, fecha_fin, estado, id_abogado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caso.getNombreCaso());
            stmt.setString(2, caso.getDescripcion());
            stmt.setDate(3, caso.getFechaInicio());
            stmt.setDate(4, caso.getFechaFin());
            stmt.setString(5, caso.getEstado());
            stmt.setInt(6, caso.getIdAbogado());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar un caso existente
    public void actualizarCaso(Caso caso) throws SQLException {
        String sql = "UPDATE casos SET nombre_caso = ?, descripcion = ?, fecha_inicio = ?, fecha_fin = ?, estado = ?, id_abogado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caso.getNombreCaso());
            stmt.setString(2, caso.getDescripcion());
            stmt.setDate(3, caso.getFechaInicio());
            stmt.setDate(4, caso.getFechaFin());
            stmt.setString(5, caso.getEstado());
            stmt.setInt(6, caso.getIdAbogado());
            stmt.setInt(7, caso.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un caso
    public void eliminarCaso(int id) throws SQLException {
        String sql = "DELETE FROM casos WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Caso obtenerPorId(int id) throws SQLException {
        Caso caso = null;
        String sql = "SELECT * FROM casos WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    caso = new Caso(
                        rs.getInt("id"),
                        rs.getString("nombre_caso"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getString("estado"),
                        rs.getInt("id_abogado")
                    );
                }
            }
        }
        return caso;
    }
}
