package com.mi_abogados;

import com.mi_abogados.Dao.DocumentoDAO;
import com.mi_abogados.models.Documento;
import com.mi_abogados.Dao.CasoDAO;
import com.mi_abogados.models.Caso;

import java.sql.SQLException;
import java.util.List;

public class DocumentoTest {

    public static void main(String[] args) {
        DocumentoDAO documentoDAO = new DocumentoDAO();
        CasoDAO casoDAO = new CasoDAO();
        
        try {
            // Verificar que existe un caso al cual asignar el documento
            List<Caso> casos = casoDAO.obtenerTodos();
            if (casos.isEmpty()) {
                System.out.println("No hay casos disponibles en la base de datos. Por favor, crea un caso primero.");
                return;
            }

            // Usar el ID del primer caso para el id_caso
            int idCasoExistente = casos.get(0).getId();
            System.out.println("Usando id_caso existente: " + idCasoExistente);

            // 1. Probar agregar un nuevo documento
            Documento nuevoDocumento = new Documento(0, "Documento de Prueba", "Tipo A", null, idCasoExistente);
            documentoDAO.agregarDocumento(nuevoDocumento);
            System.out.println("Documento agregado con éxito.");

            // 2. Probar obtener todos los documentos
            List<Documento> documentos = documentoDAO.obtenerTodos();
            System.out.println("Lista de Documentos:");
            for (Documento documento : documentos) {
                System.out.println(documento);
            }

            // 3. Probar obtener un documento por ID (actualiza el ID si es necesario)
            int id = documentos.get(0).getId();
            Documento documento = documentoDAO.obtenerPorId(id);
            if (documento != null) {
                System.out.println("Documento encontrado: " + documento);
            } else {
                System.out.println("Documento con ID " + id + " no encontrado.");
            }

            // 4. Probar actualizar un documento
            documento.setTipoDocumento("Tipo B");
            documentoDAO.actualizarDocumento(documento);
            System.out.println("Documento actualizado con éxito.");

            // 5. Probar eliminar un documento
            documentoDAO.eliminarDocumento(documento.getId());
            System.out.println("Documento eliminado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
