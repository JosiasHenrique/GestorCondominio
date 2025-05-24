package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.MesDevidoDAO;
import com.josias.gestorcondominio.dao.MesDevidoDAOImpl;
import com.josias.gestorcondominio.model.MesDevido;
import java.util.List;

public class MesDevidoController {

    private final MesDevidoDAO mesDevidoDAO;

    public MesDevidoController() {
        this.mesDevidoDAO = new MesDevidoDAOImpl();
    }

    public boolean inserirMesDevido(MesDevido mesDevido) {
        return mesDevidoDAO.inserirMesDevido(mesDevido);
    }

    public boolean excluirMesDevido(int id) {
        return mesDevidoDAO.excluirMesDevido(id);
    }

    public List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) {
        List<MesDevido> mesesDevidos = mesDevidoDAO.listarMesesDevidosPorResidencia(residenciaId);
        return mesesDevidos;
    }
}
