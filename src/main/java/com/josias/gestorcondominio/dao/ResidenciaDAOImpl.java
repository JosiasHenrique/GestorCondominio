package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Residencia;
import com.josias.gestorcondominio.persistence.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResidenciaDAOImpl implements ResidenciaDAO {

    private static final Logger logger = Logger.getLogger(ResidenciaDAOImpl.class.getName());

    @Override
    public void inserirResidencia(Residencia residencia, int idProprietario) throws SQLException {
        String sql = "INSERT INTO Residencia (rua, numero, cep, proprietario_id) VALUES (?, ?, ?, ?)";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getCep());
            stmt.setInt(4, idProprietario);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir residência, nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE.SEVERE, "Erro ao inserir residência", e);
            throw e;
        }
    }

    @Override
    public List<Residencia> findAllResidencias() throws SQLException {
        String sql = "SELECT id, rua, numero, cep, proprietario_id FROM Residencia";
        List<Residencia> residencias = new ArrayList<>();

        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residencia r = new Residencia();
                r.setId(rs.getInt("id"));
                r.setRua(rs.getString("rua"));
                r.setNumero(rs.getInt("numero"));
                r.setCep(rs.getString("cep"));
                r.getProprietario().setId(rs.getInt("proprietario_id"));
                residencias.add(r);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar residências", e);
            throw e;
        }
        return residencias;
    }

    @Override
    public void atualizarResidencia(Residencia residencia) throws SQLException {
        String sql = "UPDATE Residencia SET rua = ?, numero = ?, cep = ?, proprietario_id = ? WHERE id = ?";
        try {

            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getCep());
            stmt.setInt(4, residencia.getProprietario().getId());
            stmt.setInt(5, residencia.getId());

            int updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new SQLException("Nenhuma residência encontrada para o ID: " + residencia.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar residência", e);
            throw e;
        }
    }

    @Override
    public void excluirResidencia(int idResidencia) throws SQLException {
        String sql = "DELETE FROM Residencia WHERE id = ?";
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idResidencia);
            int deleted = stmt.executeUpdate();
            if (deleted == 0) {
                throw new SQLException("Não foi possível excluir; ID inválido: " + idResidencia);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir residência", e);
            throw e;
        }
    }
}
