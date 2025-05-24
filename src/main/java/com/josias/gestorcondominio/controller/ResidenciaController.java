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

    public void inserirResidencia(Residencia residencia, int idProprietario) throws SQLException {
        residenciaDAO.inserirResidencia(residencia, idProprietario);
        logger.info("Residência adicionada com sucesso!");
    }

    public List<Residencia> listarResidencias() throws SQLException {
        return residenciaDAO.findAllResidencias();
    }

    public void atualizarResidencia(Residencia residencia) throws SQLException {
        residenciaDAO.atualizarResidencia(residencia);
        logger.info("Residência atualizada com sucesso!");
    }

    public void excluirResidencia(int idResidencia) throws SQLException {
        residenciaDAO.excluirResidencia(idResidencia);
        logger.info("Residência excluída com sucesso!");
    }
}
