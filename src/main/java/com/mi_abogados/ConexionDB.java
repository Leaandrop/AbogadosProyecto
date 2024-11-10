package com.mi_abogados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sql_abogado";
    private static final String USER = "root";  // Reemplaza con tu usuario MySQL
    private static final String PASSWORD = "";  // Reemplaza con tu contraseña MySQL

    public static Connection conectar() throws SQLException {
        System.out.println("Intentando conectarse a la base de datos...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            System.out.println("Iniciando el programa...");
            conexion = conectar();
            System.out.println("Conexión exitosa a la base de datos sql_abogado");
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos");
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar la conexión
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión cerrada correctamente.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
