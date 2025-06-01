package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.MesDevido;
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

public class MesDevidoDAO {

    private static final Logger logger = Logger.getLogger(MesDevidoDAO.class.getName());

    public boolean inserirMesDevido(MesDevido mesDevido) {
        String sql = "INSERT INTO mes_devido (residencia_id, mes, ano, valor) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

            stmt.setInt(1, mesDevido.getResidencia().getId());
            stmt.setInt(2, mesDevido.getMes());
            stmt.setInt(3, mesDevido.getAno());
            stmt.setDouble(4, mesDevido.getValor());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir mês devido", e);
            return false;
        }
    }

    public boolean excluirMesDevido(int id) {
        String sql = "DELETE FROM mes_devido WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir mês devido", e);
            return false;
        }
    }

    public List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) {
        String sql = "SELECT id, residencia_id, mes, ano, valor FROM mes_devido WHERE residencia_id = ?";
        List<MesDevido> mesesDevidos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){


            stmt.setInt(1, residenciaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MesDevido mesDevido = new MesDevido();
                    mesDevido.setId(rs.getInt("id"));
                    mesDevido.setMes(rs.getInt("mes"));
                    mesDevido.setAno(rs.getInt("ano"));
                    mesDevido.setValor(rs.getDouble("valor"));

                    int resId = rs.getInt("residencia_id");

                    if (!rs.wasNull()) {
                        Residencia r = new Residencia();
                        r.setId(resId);
                    }

                    mesesDevidos.add(mesDevido);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar meses devidos por residência", e);
        }
        return mesesDevidos;
    }

}
