package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Proprietario;
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
    public boolean inserirResidencia(Residencia residencia) {

        int idProprietario = residencia.getProprietario().getId();
        String sql = "INSERT INTO Residencia (rua, numero, cep, proprietario_id) VALUES (?, ?, ?, ?)";

        try ( Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getCep());
            stmt.setInt(4, idProprietario);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE.SEVERE, "Erro ao inserir residência", e);
            return false;
        }
    }

    @Override
    public List<Residencia> findAllResidencias() {
        String sql = "SELECT id, rua, numero, cep, proprietario_id FROM Residencia";
        List<Residencia> residencias = new ArrayList<>();

        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Residencia r = new Residencia();
                r.setId(rs.getInt("id"));
                r.setRua(rs.getString("rua"));
                r.setNumero(rs.getInt("numero"));
                r.setCep(rs.getString("cep"));

                int propId = rs.getInt("proprietario_id");

                // Verifica se o campo proprietario_id é nulo antes de associá-lo
                if (!rs.wasNull()) {
                    Proprietario p = new Proprietario();
                    p.setId(propId);
                }
                residencias.add(r);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar residências", e);
        }
        return residencias;
    }

    @Override
    public boolean atualizarResidencia(Residencia residencia) {
        String sql = "UPDATE Residencia SET rua = ?, numero = ?, cep = ?, proprietario_id = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

            stmt.setString(1, residencia.getRua());
            stmt.setInt(2, residencia.getNumero());
            stmt.setString(3, residencia.getCep());
            stmt.setInt(4, residencia.getProprietario().getId());
            stmt.setInt(5, residencia.getId());

            int updated = stmt.executeUpdate();
           return updated > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar residência", e);
            return false;
        }
    }

    @Override
    public boolean excluirResidencia(int idResidencia) {
        String sql = "DELETE FROM Residencia WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            
            stmt.setInt(1, idResidencia);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir residência", e);
            return false;
        }
    }

}
