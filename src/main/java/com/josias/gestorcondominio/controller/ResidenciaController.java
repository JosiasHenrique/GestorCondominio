package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.ResidenciaDAO;
import com.josias.gestorcondominio.dao.ResidenciaDAOImpl;
import com.josias.gestorcondominio.model.Residencia;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ResidenciaController {

    private final ResidenciaDAO residenciaDAO;
    private static final Logger logger = Logger.getLogger(ResidenciaController.class.getName());

    public ResidenciaController() {
        this.residenciaDAO = new ResidenciaDAOImpl();
    }

    public boolean inserirResidencia(Residencia residencia, int idProprietario) throws SQLException {
        return residenciaDAO.inserirResidencia(residencia, idProprietario);
    }

    public List<Residencia> listarResidencias() throws SQLException {
        return residenciaDAO.findAllResidencias();
    }

    public boolean atualizarResidencia(Residencia residencia) throws SQLException {
       return residenciaDAO.atualizarResidencia(residencia);
    }

    public boolean excluirResidencia(int idResidencia) throws SQLException {
        return residenciaDAO.excluirResidencia(idResidencia);
    }
}
