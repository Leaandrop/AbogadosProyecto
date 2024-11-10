package com.mi_abogados;

import com.mi_abogados.Dao.AsignacionDAO;
import com.mi_abogados.models.Asignacion;
import com.mi_abogados.Dao.CasoDAO;
import com.mi_abogados.Dao.UsuarioDAO;
import com.mi_abogados.models.Caso;
import com.mi_abogados.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public class AsignacionTest {

    public static void main(String[] args) {
        AsignacionDAO asignacionDAO = new AsignacionDAO();
        CasoDAO casoDAO = new CasoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Verificar que existe un caso y un usuario para asignar
            List<Caso> casos = casoDAO.obtenerTodos();
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();

            if (casos.isEmpty()) {
                System.out.println("No hay casos disponibles en la base de datos. Por favor, crea un caso primero.");
                return;
            }

            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios disponibles en la base de datos. Por favor, crea un usuario primero.");
                return;
            }

            // Usar el ID del primer caso y usuario para la asignación
            int idCasoExistente = casos.get(0).getId();
            int idUsuarioExistente = usuarios.get(0).getId();
            System.out.println("Usando id_caso existente: " + idCasoExistente + ", id_usuario existente: " + idUsuarioExistente);

            // 1. Probar agregar una nueva asignación
            Asignacion nuevaAsignacion = new Asignacion(0, idCasoExistente, idUsuarioExistente, "Abogado", null);
            asignacionDAO.agregarAsignacion(nuevaAsignacion);
            System.out.println("Asignación agregada con éxito.");

            // 2. Probar obtener todas las asignaciones
            List<Asignacion> asignaciones = asignacionDAO.obtenerTodas();
            System.out.println("Lista de Asignaciones:");
            for (Asignacion asignacion : asignaciones) {
                System.out.println(asignacion);
            }

            // 3. Probar obtener una asignación por ID (utiliza el ID de la asignación creada)
            int id = asignaciones.get(0).getId();
            Asignacion asignacion = asignacionDAO.obtenerPorId(id);
            if (asignacion != null) {
                System.out.println("Asignación encontrada: " + asignacion);
            } else {
                System.out.println("Asignación con ID " + id + " no encontrada.");
            }

            // 4. Probar actualizar una asignación
            asignacion.setRolAsignado("Secretaria");
            asignacionDAO.actualizarAsignacion(asignacion);
            System.out.println("Asignación actualizada con éxito.");

            // 5. Probar eliminar una asignación
            asignacionDAO.eliminarAsignacion(asignacion.getId());
            System.out.println("Asignación eliminada con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
