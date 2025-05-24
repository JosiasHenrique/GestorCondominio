package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.MesDevido;
import java.sql.SQLException;
import java.util.List;

public interface MesDevidoDAO {
    
    boolean inserirMesDevido(MesDevido mesDevido) throws SQLException;
    boolean excluirMesDevido(int id) throws SQLException;
    List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) throws SQLException;
}
