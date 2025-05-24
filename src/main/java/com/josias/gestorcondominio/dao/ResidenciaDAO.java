package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Residencia;
import java.sql.SQLException;
import java.util.List;

public interface ResidenciaDAO {
    
    void inserirResidencia(Residencia r, int idProprietario) throws SQLException;
    List<Residencia> findAllResidencias() throws SQLException;
    void atualizarResidencia(Residencia r) throws SQLException;
    void excluirResidencia(int idResidencia) throws SQLException;
}
