package com.mi_abogados.servlets;

import com.mi_abogados.Dao.AsignacionDAO;
import com.mi_abogados.models.Asignacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/asignaciones")
public class AsignacionServlet extends HttpServlet {

    private AsignacionDAO asignacionDAO;

    @Override
    public void init() {
        asignacionDAO = new AsignacionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarAsignaciones(request, response);
            } else {
                switch (action) {
                    case "new":
                        mostrarFormularioNuevo(request, response);
                        break;
                    case "edit":
                        mostrarFormularioEdicion(request, response);
                        break;
                    case "delete":
                        eliminarAsignacion(request, response);
                        break;
                    default:
                        listarAsignaciones(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarAsignaciones(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Asignacion> asignaciones = asignacionDAO.obtenerTodas();
        request.setAttribute("asignaciones", asignaciones);
        request.getRequestDispatcher("/asignacion/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/asignacion/nuevo.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Asignacion asignacion = asignacionDAO.obtenerPorId(id);
        request.setAttribute("asignacion", asignacion);
        request.getRequestDispatcher("/asignacion/editar.jsp").forward(request, response);
    }

    private void eliminarAsignacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        asignacionDAO.eliminarAsignacion(id);
        response.sendRedirect("asignaciones");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarAsignaciones(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertarAsignacion(request, response);
                        break;
                    case "update":
                        actualizarAsignacion(request, response);
                        break;
                    default:
                        listarAsignaciones(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void insertarAsignacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idCaso = Integer.parseInt(request.getParameter("id_caso"));
        int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
        String rolAsignado = request.getParameter("rol_asignado");

        Asignacion nuevaAsignacion = new Asignacion(0, idCaso, idUsuario, rolAsignado, null);
        asignacionDAO.agregarAsignacion(nuevaAsignacion);
        response.sendRedirect("asignaciones");
    }

    private void actualizarAsignacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idCaso = Integer.parseInt(request.getParameter("id_caso"));
        int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
        String rolAsignado = request.getParameter("rol_asignado");

        Asignacion asignacion = new Asignacion(id, idCaso, idUsuario, rolAsignado, null);
        asignacionDAO.actualizarAsignacion(asignacion);
        response.sendRedirect("asignaciones");
    }
}
