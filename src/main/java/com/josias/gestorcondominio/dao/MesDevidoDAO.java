package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.MesDevido;
import java.sql.SQLException;
import java.util.List;

public interface MesDevidoDAO {
    
    void inserirMesDevido(MesDevido mesDevido) throws SQLException;
    void excluirMesDevido(int id) throws SQLException;
    List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) throws SQLException;
}
