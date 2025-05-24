package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAOImpl implements PessoaDAO {

    private static final Logger logger = Logger.getLogger(PessoaDAOImpl.class.getName());

    @Override
    public void inserirProprietario(Proprietario proprietario) throws SQLException {
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
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir proprietário, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir proprietário", e);
            throw e;
        }
    }

    @Override
    public void inserirMorador(Morador morador, int residenciaId) throws SQLException {
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
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir morador, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir morador", e);
            throw e;
        }
    }

    @Override
    public void atualizarProprietario(Proprietario proprietario) throws SQLException {
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
            if (updated == 0) {
                throw new SQLException("Nenhum proprietário encontrado para o ID: " + proprietario.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar proprietário", e);
            throw e;
        }
    }

    @Override
    public void atualizarMorador(Morador morador) throws SQLException {
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
            if (updated == 0) {
                throw new SQLException("Nenhum morador encontrado para o ID: " + morador.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar morador", e);
            throw e;
        }
    }

    @Override
    public void excluirMorador(int idMorador) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Morador'";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idMorador);
            int deleted = stmt.executeUpdate();
            if (deleted == 0) {
                throw new SQLException("Não foi possível excluir morador; ID inválido: " + idMorador);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir morador", e);
            throw e;
        }
    }

    @Override
    public void excluirProprietario(int idProprietario) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Proprietario'";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idProprietario);
            int deleted = stmt.executeUpdate();
            if (deleted == 0) {
                throw new SQLException("Não foi possível excluir proprietário; ID inválido: " + idProprietario);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir proprietário", e);
            throw e;
        }
    }
}
