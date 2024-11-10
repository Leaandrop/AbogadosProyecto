package com.mi_abogados.Dao;

import com.mi_abogados.ConexionDB;
import com.mi_abogados.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),         // Asegúrate de usar el nombre exacto del campo en la tabla
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    rs.getString("rol"),
                    rs.getDate("fecha_registro")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // Método para agregar un usuario
    public void agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (email, password, nombre, rol, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getRol());
            stmt.setDate(5, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.executeUpdate();
        }
    }

    // Método para actualizar un usuario existente
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET email = ?, password = ?, nombre = ?, rol = ?, fecha_registro = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getRol());
            stmt.setDate(5, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.setInt(6, usuario.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Usuario obtenerPorId(int id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getDate("fecha_registro")
                    );
                }
            }
        }
        return usuario;
    }
    
}
