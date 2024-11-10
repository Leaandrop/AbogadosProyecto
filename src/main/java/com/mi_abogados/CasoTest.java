package com.mi_abogados;

import com.mi_abogados.Dao.CasoDAO;
import com.mi_abogados.models.Caso;
import com.mi_abogados.Dao.UsuarioDAO;
import com.mi_abogados.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public class CasoTest {

    public static void main(String[] args) {
        CasoDAO casoDAO = new CasoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        try {
            // Verificar que existe un usuario que puede ser asignado como abogado
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios disponibles en la base de datos. Por favor, crea un usuario primero.");
                return;
            }

            // Usar el ID del primer usuario como id_abogado
            int idAbogadoExistente = usuarios.get(0).getId();
            System.out.println("Usando id_abogado existente: " + idAbogadoExistente);

            // 1. Verificar si hay algún caso en la base de datos
            List<Caso> casos = casoDAO.obtenerTodos();
            Caso caso = null;

            if (!casos.isEmpty()) {
                // Si hay casos, obtener el primero en la lista
                caso = casos.get(0);
                System.out.println("Caso encontrado: " + caso);
            } else {
                // Si no hay casos, crear uno nuevo con un id_abogado válido
                caso = new Caso(0, "Caso de Prueba", "Descripción de prueba", null, null, "Abierto", idAbogadoExistente);
                casoDAO.agregarCaso(caso);
                System.out.println("Caso agregado con éxito.");
                
                // Recargar el caso desde la base de datos para obtener el ID asignado
                casos = casoDAO.obtenerTodos();
                if (!casos.isEmpty()) {
                    caso = casos.get(0); // Ahora debería haber al menos un caso en la lista
                }
            }

            // 2. Probar obtener un caso por ID solo si `caso` no es null
            if (caso != null) {
                caso = casoDAO.obtenerPorId(caso.getId());
                if (caso != null) {
                    System.out.println("Caso obtenido por ID: " + caso);
                } else {
                    System.out.println("No se encontró el caso con ID: ");
                }

                // 3. Probar actualizar un caso
                caso.setDescripcion("Descripción actualizada");
                casoDAO.actualizarCaso(caso);
                System.out.println("Caso actualizado con éxito: " + caso);

                // 4. Probar eliminar un caso
                casoDAO.eliminarCaso(caso.getId());
                System.out.println("Caso eliminado con éxito.");
            } else {
                System.out.println("No se encontró o no se pudo crear ningún caso para realizar pruebas.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
