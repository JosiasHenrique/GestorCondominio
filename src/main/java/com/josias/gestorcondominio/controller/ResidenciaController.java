package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.ResidenciaDAO;
import com.josias.gestorcondominio.dao.ResidenciaDAOImpl;
import com.josias.gestorcondominio.model.Residencia;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResidenciaController {

    private final ResidenciaDAO residenciaDAO;
    private static final Logger logger = Logger.getLogger(ResidenciaController.class.getName());

    public ResidenciaController() {
        this.residenciaDAO = new ResidenciaDAOImpl();
    }

    public boolean inserirResidencia(Residencia residencia) {
        
        int idProprietario = residencia.getProprietario().getId();
        
        if (idProprietario <= 0) {
            logger.log(Level.WARNING, "ID do proprietário inválido: {0}", idProprietario);
            return false;
        }

        if (residencia.getRua() == null || residencia.getRua().trim().isEmpty()) {
            logger.log(Level.WARNING, "O campo 'rua' é obrigatório para a residência.");
            return false;
        }

        if (residencia.getNumero() <= 0) {
            logger.log(Level.WARNING, "O número da residência deve ser maior que 0. Valor fornecido: {0}", residencia.getNumero());
            return false;
        }

        if (residencia.getCep() == null || residencia.getCep().trim().isEmpty()) {
            logger.log(Level.WARNING, "O campo 'cep' é obrigatório para a residência.");
            return false;
        }

        return residenciaDAO.inserirResidencia(residencia);
    }

    public List<Residencia> listarResidencias() {

        List<Residencia> residencias = residenciaDAO.findAllResidencias();

        if (residencias == null || residencias.isEmpty()) {
            logger.log(Level.INFO, "Nenhuma residência encontrada no banco de dados.");
            return Collections.emptyList();
        }

        return residencias;

    }

    public boolean atualizarResidencia(Residencia residencia) {

        if (residencia.getRua() == null || residencia.getRua().trim().isEmpty()) {
            logger.log(Level.WARNING, "O campo 'rua' é obrigatório para atualizar a residência.");
            return false;
        }

        if (residencia.getCep() == null || residencia.getCep().trim().isEmpty()) {
            logger.log(Level.WARNING, "O campo 'cep' é obrigatório para atualizar a residência.");
            return false;
        }

        if (residencia.getNumero() <= 0) {
            logger.log(Level.WARNING, "O número da residência deve ser maior que 0. Valor fornecido: {0}", residencia.getNumero());
            return false;
        }
        return residenciaDAO.atualizarResidencia(residencia);
    }

    public boolean excluirResidencia(int idResidencia) {
        
        if (idResidencia <= 0) {
            logger.log(Level.WARNING, "ID da residência inválido: {0}", idResidencia);
            return false;
        }
        return residenciaDAO.excluirResidencia(idResidencia);
    }
}
