package com.mi_abogados.servlets;

import com.mi_abogados.Dao.CasoDAO;
import com.mi_abogados.models.Caso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/casos")
public class CasoServlet extends HttpServlet {

    private CasoDAO casoDAO;

    @Override
    public void init() {
        casoDAO = new CasoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarCasos(request, response);
            } else {
                switch (action) {
                    case "new":
                        mostrarFormularioNuevo(request, response);
                        break;
                    case "edit":
                        mostrarFormularioEdicion(request, response);
                        break;
                    case "delete":
                        eliminarCaso(request, response);
                        break;
                    default:
                        listarCasos(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarCasos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Caso> casos = casoDAO.obtenerTodos();
        request.setAttribute("casos", casos);
        request.getRequestDispatcher("/caso/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/caso/nuevo.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Caso caso = casoDAO.obtenerPorId(id);
        request.setAttribute("caso", caso);
        request.getRequestDispatcher("/caso/editar.jsp").forward(request, response);
    }

    private void eliminarCaso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        casoDAO.eliminarCaso(id);
        response.sendRedirect("casos");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarCasos(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertarCaso(request, response);
                        break;
                    case "update":
                        actualizarCaso(request, response);
                        break;
                    default:
                        listarCasos(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void insertarCaso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombreCaso = request.getParameter("nombre_caso");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        int idAbogado = Integer.parseInt(request.getParameter("id_abogado"));

        Caso nuevoCaso = new Caso(0, nombreCaso, descripcion, null, null, estado, idAbogado);
        casoDAO.agregarCaso(nuevoCaso);
        response.sendRedirect("casos");
    }

    private void actualizarCaso(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreCaso = request.getParameter("nombre_caso");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        int idAbogado = Integer.parseInt(request.getParameter("id_abogado"));

        Caso caso = new Caso(id, nombreCaso, descripcion, null, null, estado, idAbogado);
        casoDAO.actualizarCaso(caso);
        response.sendRedirect("casos");
    }
}
