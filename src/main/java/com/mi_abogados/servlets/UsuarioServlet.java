package com.mi_abogados.servlets;

import com.mi_abogados.Dao.UsuarioDAO;
import com.mi_abogados.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarUsuarios(request, response);
            } else {
                switch (action) {
                    case "new":
                        mostrarFormularioNuevo(request, response);
                        break;
                    case "edit":
                        mostrarFormularioEdicion(request, response);
                        break;
                    case "delete":
                        eliminarUsuario(request, response);
                        break;
                    default:
                        listarUsuarios(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/usuario/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/usuario/nuevo.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.obtenerPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/usuario/editar.jsp").forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect("usuarios");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listarUsuarios(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertarUsuario(request, response);
                        break;
                    case "update":
                        actualizarUsuario(request, response);
                        break;
                    default:
                        listarUsuarios(request, response);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String rol = request.getParameter("rol");

        // Verificar que el rol es válido
        if (!esRolValido(rol)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rol no válido.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(0, email, password, nombre, rol, new java.sql.Date(System.currentTimeMillis()));
        usuarioDAO.agregarUsuario(nuevoUsuario);
        response.sendRedirect("usuarios");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String rol = request.getParameter("rol");

        // Verificar que el rol es válido
        if (!esRolValido(rol)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rol no válido.");
            return;
        }

        Usuario usuario = new Usuario(id, email, password, nombre, rol, new java.sql.Date(System.currentTimeMillis()));
        usuarioDAO.actualizarUsuario(usuario);
        response.sendRedirect("usuarios");
    }

    private boolean esRolValido(String rol) {
        return rol.equals("Administrador") || rol.equals("Abogado") || rol.equals("Secretaria") || rol.equals("Usuario");
    }
}
