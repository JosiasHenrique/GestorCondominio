package com.josias.gestorcondominio.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorDAO {

    private static GestorDAO instancia;

    private static final String URL = "jdbc:mysql://localhost:3306/condominio_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    
    private GestorDAO() {
        
    }

    public static GestorDAO getInstance() {
        if (instancia == null) {
            synchronized (GestorDAO.class) {
                if (instancia == null) {
                    instancia = new GestorDAO();
                }
            }
        }
        return instancia;
    }

    //Verificar se esse metodo fere o padrao singleton
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public List<Proprietario> findAll() throws SQLException {
        List<Proprietario> proprietarios = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                proprietarios.add(new Proprietario(rs.getInt("id"), rs.getInt("idade"), rs.getString("nome"), rs.getString("rg"), rs.getString("cpf")));
            }
        }
        return proprietarios;
    }
}
