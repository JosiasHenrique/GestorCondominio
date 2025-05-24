package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.dao.ResidenciaDAO;
import com.josias.gestorcondominio.dao.ResidenciaDAOImpl;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.model.Residencia;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class PessoaController {

    private final PessoaDAO pessoaDAO;
    private final ResidenciaDAO residenciaDAO;
    private static final Logger logger = Logger.getLogger(PessoaController.class.getName());

    public PessoaController() {
        this.pessoaDAO = new PessoaDAOImpl();
        this.residenciaDAO = new ResidenciaDAOImpl();
    }

    public void inserirProprietario(Proprietario p) throws SQLException {
        pessoaDAO.inserirProprietario(p);
    }

    public void atualizarProprietario(Proprietario p) throws SQLException {
        pessoaDAO.atualizarProprietario(p);
    }

    public void excluirProprietario(int idProprietario) throws SQLException {
        pessoaDAO.excluirProprietario(idProprietario);
    }

    public void inserirMorador(Morador m, int residenciaId) throws SQLException {
        Residencia r = residenciaDAO.findAllResidencias().stream()
                .filter(res -> res.getId() == residenciaId)
                .findFirst()
                .orElse(null);
        if (r == null) {
            throw new SQLException("Residência com ID " + residenciaId + " não encontrada.");
        }
        pessoaDAO.inserirMorador(m, residenciaId);
    }

    public void atualizarMorador(Morador m) throws SQLException {
        int resId = m.getResidencia().getId();
        Residencia r = residenciaDAO.findAllResidencias()
                .stream()
                .filter(res -> res.getId() == resId)
                .findFirst()
                .orElse(null);
        if (r == null) {
            throw new SQLException("Residência com ID " + resId + " não encontrada.");
        }
        pessoaDAO.atualizarMorador(m);
    }

    public List<Morador> listarMoradoresPorResidencia(int residenciaId) throws SQLException {
        logger.info("Listando moradores da residência com ID: " + residenciaId);
        return pessoaDAO.listarMoradoresPorResidencia(residenciaId);
    }

    public void excluirMorador(int idMorador) throws SQLException {
        pessoaDAO.excluirMorador(idMorador);
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) throws SQLException {
        logger.info("Obtendo proprietário da residência com ID: " + residenciaId);
        return pessoaDAO.obterProprietarioPorResidencia(residenciaId);
    }

}
