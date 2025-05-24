package com.josias.gestorcondominio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instancia;
    private static final String URL = "jdbc:mysql://localhost:3306/condominio_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private ConnectionManager() {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("Configurações do banco de dados não foram definidas corretamente.");
        }
    }

    public static ConnectionManager getInstance() {
        if (instancia == null) {
            synchronized (ConnectionManager.class) {
                if (instancia == null) {
                    instancia = new ConnectionManager();
                }
            }
        }
        return instancia;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
