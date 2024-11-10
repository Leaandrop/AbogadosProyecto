package com.mi_abogados.Dao;

import com.mi_abogados.ConexionDB;
import com.mi_abogados.models.Asignacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionDAO {

    // Método para obtener todas las asignaciones
    public List<Asignacion> obtenerTodas() throws SQLException {
        List<Asignacion> asignaciones = new ArrayList<>();
        String sql = "SELECT * FROM asignaciones";
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Asignacion asignacion = new Asignacion(
                    rs.getInt("id"),
                    rs.getInt("id_caso"),
                    rs.getInt("id_usuario"),
                    rs.getString("rol_asignado"),
                    rs.getTimestamp("fecha_asignacion")
                );
                asignaciones.add(asignacion);
            }
        }
        return asignaciones;
    }

    // Método para agregar una asignación
    public void agregarAsignacion(Asignacion asignacion) throws SQLException {
        String sql = "INSERT INTO asignaciones (id_caso, id_usuario, rol_asignado, fecha_asignacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, asignacion.getIdCaso());
            stmt.setInt(2, asignacion.getIdUsuario());
            stmt.setString(3, asignacion.getRolAsignado());
            stmt.setTimestamp(4, asignacion.getFechaAsignacion());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar una asignación existente
    public void actualizarAsignacion(Asignacion asignacion) throws SQLException {
        String sql = "UPDATE asignaciones SET id_caso = ?, id_usuario = ?, rol_asignado = ?, fecha_asignacion = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, asignacion.getIdCaso());
            stmt.setInt(2, asignacion.getIdUsuario());
            stmt.setString(3, asignacion.getRolAsignado());
            stmt.setTimestamp(4, asignacion.getFechaAsignacion());
            stmt.setInt(5, asignacion.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una asignación
    public void eliminarAsignacion(int id) throws SQLException {
        String sql = "DELETE FROM asignaciones WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Asignacion obtenerPorId(int id) throws SQLException {
        Asignacion asignacion = null;
        String sql = "SELECT * FROM asignaciones WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    asignacion = new Asignacion(
                        rs.getInt("id"),
                        rs.getInt("id_caso"),
                        rs.getInt("id_usuario"),
                        rs.getString("rol_asignado"),
                        rs.getTimestamp("fecha_asignacion")
                    );
                }
            }
        }
        return asignacion;
    }
}
