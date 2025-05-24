package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAOImpl implements PessoaDAO {

    private static final Logger logger = Logger.getLogger(PessoaDAOImpl.class.getName());

    @Override
    public boolean inserirProprietario(Proprietario proprietario) throws SQLException {
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) "
                + "VALUES (?, ?, ?, ?, 'Proprietario', NULL)";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, proprietario.getNome());
            stmt.setInt(2, proprietario.getIdade());
            stmt.setString(3, proprietario.getRg());
            stmt.setString(4, proprietario.getCpf());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir proprietário", e);
            return false;
        }
    }

    @Override
    public boolean inserirMorador(Morador morador, int residenciaId) throws SQLException {
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) "
                + "VALUES (?, ?, ?, ?, 'Morador', ?)";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, morador.getNome());
            stmt.setInt(2, morador.getIdade());
            stmt.setString(3, morador.getRg());
            stmt.setString(4, morador.getCpf());
            stmt.setInt(5, residenciaId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir morador", e);
            return false;
        }
    }

    @Override
    public boolean atualizarProprietario(Proprietario proprietario) throws SQLException {
        String sql = "UPDATE Pessoa SET nome = ?, idade = ?, rg = ?, cpf = ? "
                + "WHERE id = ? AND tipo = 'Proprietario'";
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, proprietario.getNome());
            stmt.setInt(2, proprietario.getIdade());
            stmt.setString(3, proprietario.getRg());
            stmt.setString(4, proprietario.getCpf());
            stmt.setInt(5, proprietario.getId());

            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar proprietário", e);
            return false;
        }
    }

    @Override
    public boolean atualizarMorador(Morador morador) throws SQLException {
        String sql = "UPDATE Pessoa SET nome = ?, idade = ?, rg = ?, cpf = ?, residencia_id = ? "
                + "WHERE id = ? AND tipo = 'Morador'";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, morador.getNome());
            stmt.setInt(2, morador.getIdade());
            stmt.setString(3, morador.getRg());
            stmt.setString(4, morador.getCpf());
            stmt.setInt(5, morador.getResidencia().getId());
            stmt.setInt(6, morador.getId());

            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar morador", e);
            return false;
        }
    }

    @Override
    public boolean excluirMorador(int idMorador) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Morador'";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idMorador);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir morador", e);
            return false;
        }
    }

    @Override
    public boolean excluirProprietario(int idProprietario) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Proprietario'";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idProprietario);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir proprietário", e);
            return false;
        }
    }

    @Override
    public List<Morador> listarMoradoresPorResidencia(int residenciaId) throws SQLException {
        String sql = "SELECT id, nome, idade, rg, cpf FROM Pessoa WHERE residencia_id = ? AND tipo = 'Morador'";
        List<Morador> moradores = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, residenciaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Morador morador = new Morador();
                morador.setId(rs.getInt("id"));
                morador.setNome(rs.getString("nome"));
                morador.setIdade(rs.getInt("idade"));
                morador.setRg(rs.getString("rg"));
                morador.setCpf(rs.getString("cpf"));
                moradores.add(morador);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar moradores da residência com ID " + residenciaId, e);
            throw e;
        }

        return moradores;
    }

    @Override
    public Proprietario obterProprietarioPorResidencia(int residenciaId) throws SQLException {
        String sql = "SELECT p.id, p.idade, p.nome, p.rg, p.cpf "
                + "FROM Pessoa p "
                + "JOIN Residencia r ON p.id = r.proprietario_id "
                + "WHERE r.id = ?";
        Proprietario proprietario = null;

        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, residenciaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                proprietario = new Proprietario();
                proprietario.setId(rs.getInt("id"));
                proprietario.setIdade(rs.getInt("idade"));
                proprietario.setNome(rs.getString("nome"));
                proprietario.setRg(rs.getString("rg"));
                proprietario.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao obter proprietário da residência com ID " + residenciaId, e);
            throw e;
        }

        return proprietario;
    }
}
