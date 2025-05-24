package com.josias.gestorcondominio.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GestorDAO {

    private static GestorDAO instancia;
    private static final Logger logger = Logger.getLogger(GestorDAO.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/condominio_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private GestorDAO() {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("Configurações do banco de dados não foram definidas.");
        }
    }

    public static GestorDAO getInstance() {
        if (instancia == null) {
            synchronized (GestorDAO.class) {
                if (instancia == null) {
                    instancia = new GestorDAO();
                    logger.info("Instância de GestorDAO criada.");
                }
            }
        }
        return instancia;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Tentando conectar ao banco de dados...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Residencia> findAllResidencias() throws SQLException {
        logger.info("Buscando todas as residências...");
        List<Residencia> residencias = new ArrayList<>();
        String sql = "SELECT id, rua, numero, cep FROM Residencia";

        try {

            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residencia residencia = new Residencia();
                residencia.setId(rs.getInt("id"));
                residencia.setRua(rs.getString("rua"));
                residencia.setNumero(rs.getInt("numero"));
                residencia.setCep(rs.getString("cep"));
                residencias.add(residencia);
            }

        } catch (SQLException e) {
            logger.severe("Erro ao buscar residências: " + e.getMessage());
            throw e;
        }

        return residencias;
    }

    public void inserirProprietario(Proprietario proprietario) throws SQLException {
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES (?, ?, ?, ?, 'Proprietario', NULL)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proprietario.getNome());
            stmt.setInt(2, proprietario.getIdade());
            stmt.setString(3, proprietario.getRg());
            stmt.setString(4, proprietario.getCpf());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir proprietário, nenhuma linha afetada.");
            }
        }
    }

    public void inserirResidencia(Residencia residencia, int idProprietario) throws SQLException {
        String sql = "INSERT INTO Residencia (rua, numero, cep, proprietario_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getCep());
            stmt.setInt(4, idProprietario);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir residência, nenhuma linha afetada.");
            }
        }
    }

    public void inserirMorador(Morador morador, int residenciaId) throws SQLException {
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES (?, ?, ?, ?, 'Morador', ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, morador.getNome());
            stmt.setInt(2, morador.getIdade());
            stmt.setString(3, morador.getRg());
            stmt.setString(4, morador.getCpf());
            stmt.setInt(5, residenciaId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir morador, nenhuma linha afetada.");
            }
        }
    }

}
