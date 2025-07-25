package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Pessoa;
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

public class PessoaDAO {

    private static final Logger logger = Logger.getLogger(PessoaDAO.class.getName());

    public boolean inserirProprietario(Proprietario proprietario) {
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) "
                + "VALUES (?, ?, ?, ?, 'Proprietario', NULL)";
        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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

    public boolean inserirMorador(Morador morador) {
        int residenciaId = morador.getResidencia().getId();
        String sql = "INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) "
                + "VALUES (?, ?, ?, ?, 'Morador', ?)";
        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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

    public boolean atualizarProprietario(Proprietario proprietario) {
        String sql = "UPDATE Pessoa SET nome = ?, idade = ?, rg = ?, cpf = ? "
                + "WHERE id = ? AND tipo = 'Proprietario'";

        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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

    public boolean atualizarMorador(Morador morador) {
        String sql = "UPDATE Pessoa SET nome = ?, idade = ?, rg = ?, cpf = ?, residencia_id = ? "
                + "WHERE id = ? AND tipo = 'Morador'";
        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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

    public boolean excluirMorador(int idMorador) {
        String sql = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Morador'";

        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, idMorador);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir morador", e);
            return false;
        }
    }

    public boolean excluirProprietario(int idProprietario) {
        String sqlDesvincular = "UPDATE Residencia SET proprietario_id = NULL WHERE proprietario_id = ?";
        String sqlExcluir = "DELETE FROM Pessoa WHERE id = ? AND tipo = 'Proprietario'";

        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtDesvincular = conn.prepareStatement(sqlDesvincular); PreparedStatement stmtExcluir = conn.prepareStatement(sqlExcluir)) {

                stmtDesvincular.setInt(1, idProprietario);
                stmtDesvincular.executeUpdate();

                stmtExcluir.setInt(1, idProprietario);
                int deleted = stmtExcluir.executeUpdate();

                conn.commit();
                return deleted > 0;

            } catch (SQLException e) {
                conn.rollback();
                logger.log(Level.SEVERE, "Erro ao excluir proprietário", e);
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir proprietário", e);
            return false;
        }
    }

    public List<Morador> listarMoradoresPorResidencia(int residenciaId) {
        String sql = "SELECT id, nome, idade, rg, cpf FROM Pessoa WHERE residencia_id = ? AND tipo = 'Morador'";
        List<Morador> moradores = new ArrayList<>();

        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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
        }

        return moradores;
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) {
        String sql = "SELECT p.id, p.idade, p.nome, p.rg, p.cpf "
                + "FROM Pessoa p "
                + "JOIN Residencia r ON p.id = r.proprietario_id "
                + "WHERE r.id = ?";
        Proprietario proprietario = null;

        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

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
        }

        return proprietario;
    }

    public List<Pessoa> listarPessoas(String tipo) {
        String sql = "SELECT id, nome, idade, rg, cpf, tipo FROM Pessoa WHERE tipo = ?";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = ConnectionManager.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa pessoa;

                if ("Morador".equalsIgnoreCase(rs.getString("tipo"))) {
                    pessoa = new Morador();
                } else if ("Proprietario".equalsIgnoreCase(rs.getString("tipo"))) {
                    pessoa = new Proprietario();
                } else {
                    throw new IllegalArgumentException("Tipo de pessoa desconhecido: " + tipo);
                }

                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setIdade(rs.getInt("idade"));
                pessoa.setRg(rs.getString("rg"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar pessoas do tipo " + tipo, e);
        }

        return pessoas;
    }

}
