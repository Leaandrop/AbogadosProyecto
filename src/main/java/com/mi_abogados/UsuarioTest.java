package com.mi_abogados;

import com.mi_abogados.Dao.UsuarioDAO;
import com.mi_abogados.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioTest {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // 1. Probar agregar un nuevo usuario con un rol válido
            Usuario nuevoUsuario = new Usuario(0, "test@example.com", "password123", "Test User", "Administrador", new java.sql.Date(System.currentTimeMillis()));
            usuarioDAO.agregarUsuario(nuevoUsuario);
            System.out.println("Usuario agregado con éxito.");

            // 2. Probar obtener todos los usuarios
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            System.out.println("Lista de Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }

            // 3. Probar obtener un usuario por ID (actualiza el ID según tu base de datos)
            int id = 1; // Cambia esto por un ID que exista en tu base de datos
            Usuario usuario = usuarioDAO.obtenerPorId(id);
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario);
            } else {
                System.out.println("Usuario con ID " + id + " no encontrado.");
            }

            // 4. Probar actualizar un usuario con un rol válido
            if (usuario != null) {
                usuario.setNombre("Nombre Actualizado");
                usuario.setRol("Usuario"); // Cambiar a otro rol permitido
                usuarioDAO.actualizarUsuario(usuario);
                System.out.println("Usuario actualizado con éxito.");
            }

            // 5. Probar eliminar un usuario
            if (usuario != null) {
                usuarioDAO.eliminarUsuario(usuario.getId());
                System.out.println("Usuario eliminado con éxito.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
