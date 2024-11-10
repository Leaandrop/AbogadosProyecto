package com.mi_abogados.servlets;

import com.mi_abogados.Dao.DocumentoDAO;
import com.mi_abogados.models.Documento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/documentos")
public class DocumentoServlet extends HttpServlet {

    private DocumentoDAO documentoDAO;

    @Override
    public void init() {
        documentoDAO = new DocumentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarDocumentos(request, response);
            } else {
                switch (action) {
                    case "new":
                        mostrarFormularioNuevo(request, response);
                        break;
                    case "edit":
                        mostrarFormularioEdicion(request, response);
                        break;
                    case "delete":
                        eliminarDocumento(request, response);
                        break;
                    default:
                        listarDocumentos(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarDocumentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Documento> documentos = documentoDAO.obtenerTodos();
        request.setAttribute("documentos", documentos);
        request.getRequestDispatcher("/documento/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/documento/nuevo.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Documento documento = documentoDAO.obtenerPorId(id);
        request.setAttribute("documento", documento);
        request.getRequestDispatcher("/documento/editar.jsp").forward(request, response);
    }

    private void eliminarDocumento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        documentoDAO.eliminarDocumento(id);
        response.sendRedirect("documentos");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarDocumentos(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertarDocumento(request, response);
                        break;
                    case "update":
                        actualizarDocumento(request, response);
                        break;
                    default:
                        listarDocumentos(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void insertarDocumento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombreDocumento = request.getParameter("nombre_documento");
        String tipoDocumento = request.getParameter("tipo_documento");
        int idCaso = Integer.parseInt(request.getParameter("id_caso"));

        Documento nuevoDocumento = new Documento(0, nombreDocumento, tipoDocumento, null, idCaso);
        documentoDAO.agregarDocumento(nuevoDocumento);
        response.sendRedirect("documentos");
    }

    private void actualizarDocumento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreDocumento = request.getParameter("nombre_documento");
        String tipoDocumento = request.getParameter("tipo_documento");
        int idCaso = Integer.parseInt(request.getParameter("id_caso"));

        Documento documento = new Documento(id, nombreDocumento, tipoDocumento, null, idCaso);
        documentoDAO.actualizarDocumento(documento);
        response.sendRedirect("documentos");
    }
}
