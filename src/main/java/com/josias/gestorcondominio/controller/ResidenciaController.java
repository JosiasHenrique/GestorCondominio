package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.ResidenciaDAO;
import com.josias.gestorcondominio.dao.ResidenciaDAOImpl;
import com.josias.gestorcondominio.model.Residencia;
import java.util.List;

public class ResidenciaController {

    private final ResidenciaDAO residenciaDAO;

    public ResidenciaController() {
        this.residenciaDAO = new ResidenciaDAOImpl();
    }

    public boolean inserirResidencia(Residencia residencia, int idProprietario) {
        return residenciaDAO.inserirResidencia(residencia, idProprietario);
    }

    public List<Residencia> listarResidencias() {
        return residenciaDAO.findAllResidencias();
    }

    public boolean atualizarResidencia(Residencia residencia) {
       return residenciaDAO.atualizarResidencia(residencia);
    }

    public boolean excluirResidencia(int idResidencia) {
        return residenciaDAO.excluirResidencia(idResidencia);
    }
}
