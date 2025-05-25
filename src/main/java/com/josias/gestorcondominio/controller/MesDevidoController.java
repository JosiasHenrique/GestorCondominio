package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.MesDevidoDAO;
import com.josias.gestorcondominio.dao.MesDevidoDAOImpl;
import com.josias.gestorcondominio.model.MesDevido;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MesDevidoController {

    private final MesDevidoDAO mesDevidoDAO;
    private static final Logger logger = Logger.getLogger(MesDevidoController.class.getName());

    public MesDevidoController() {
        this.mesDevidoDAO = new MesDevidoDAOImpl();
    }

    public boolean inserirMesDevido(MesDevido mesDevido) {

        if (mesDevido.getMes() < 1 || mesDevido.getMes() > 12) {
            logger.log(Level.WARNING, "Mês inválido: {0}. Deve ser entre 1 e 12.", mesDevido.getMes());
            return false;
        }

        if (mesDevido.getAno() <= 0) {
            logger.log(Level.WARNING, "Ano inválido: {0}. Deve ser maior que 0.", mesDevido.getAno());
            return false;
        }

        if (mesDevido.getValor() <= 0) {
            logger.log(Level.WARNING, "Valor inválido: {0}. Deve ser maior que 0.", mesDevido.getValor());
            return false;
        }

        return mesDevidoDAO.inserirMesDevido(mesDevido);
    }

    public boolean excluirMesDevido(int id) {

        if (id <= 0) {
            logger.log(Level.WARNING, "Tentativa de excluir MesDevido com ID inválido: {0}", id);
            return false;
        }
        return mesDevidoDAO.excluirMesDevido(id);
    }

    public List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) {

        if (residenciaId <= 0) {
            logger.log(Level.WARNING, "ID de residência inválido: {0}", residenciaId);
            return Collections.emptyList();
        }

        List<MesDevido> mesesDevidos = mesDevidoDAO.listarMesesDevidosPorResidencia(residenciaId);
        return mesesDevidos;
    }
}
