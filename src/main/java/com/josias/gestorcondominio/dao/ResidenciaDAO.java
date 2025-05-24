package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Residencia;
import java.sql.SQLException;
import java.util.List;

public interface ResidenciaDAO {
    
    boolean inserirResidencia(Residencia r, int idProprietario) throws SQLException;
    List<Residencia> findAllResidencias() throws SQLException;
    boolean atualizarResidencia(Residencia r) throws SQLException;
    boolean excluirResidencia(int idResidencia) throws SQLException;
}
