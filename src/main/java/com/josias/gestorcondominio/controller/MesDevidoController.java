package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.MesDevidoDAO;
import com.josias.gestorcondominio.dao.MesDevidoDAOImpl;
import com.josias.gestorcondominio.model.MesDevido;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class MesDevidoController {

    private final MesDevidoDAO mesDevidoDAO;
    private static final Logger logger = Logger.getLogger(PessoaController.class.getName());

    public MesDevidoController() {
        this.mesDevidoDAO = new MesDevidoDAOImpl();
    }

    public void inserirMesDevido(MesDevido mesDevido) throws SQLException {
        mesDevidoDAO.inserirMesDevido(mesDevido);
        logger.info("Mês devido adicionado com sucesso!");
    }

    public void excluirMesDevido(int id) throws SQLException {
        mesDevidoDAO.excluirMesDevido(id);
        logger.info("Mês devido excluído com sucesso!");
    }

    public List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) throws SQLException {
        List<MesDevido> mesesDevidos = mesDevidoDAO.listarMesesDevidosPorResidencia(residenciaId);
        logger.info("Meses devidos listados com sucesso para a Residência ID: " + residenciaId);
        return mesesDevidos;
    }
}
