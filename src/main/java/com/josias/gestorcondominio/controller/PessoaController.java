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

    public boolean inserirProprietario(Proprietario p) throws SQLException {
        return pessoaDAO.inserirProprietario(p);
    }

    public boolean atualizarProprietario(Proprietario p) throws SQLException {
        return pessoaDAO.atualizarProprietario(p);
    }

    public boolean excluirProprietario(int idProprietario) throws SQLException {
        return pessoaDAO.excluirProprietario(idProprietario);
    }

    public boolean inserirMorador(Morador m, int residenciaId) throws SQLException {
        Residencia r = residenciaDAO.findAllResidencias().stream()
                .filter(res -> res.getId() == residenciaId)
                .findFirst()
                .orElse(null);
        if (r == null) {
            throw new SQLException("Residência com ID " + residenciaId + " não encontrada.");
        }
        return pessoaDAO.inserirMorador(m, residenciaId);
    }

    public boolean atualizarMorador(Morador m) throws SQLException {
        int resId = m.getResidencia().getId();
        Residencia r = residenciaDAO.findAllResidencias()
                .stream()
                .filter(res -> res.getId() == resId)
                .findFirst()
                .orElse(null);
        if (r == null) {
            throw new SQLException("Residência com ID " + resId + " não encontrada.");
        }
        return pessoaDAO.atualizarMorador(m);
    }

    public List<Morador> listarMoradoresPorResidencia(int residenciaId) throws SQLException {
        logger.info("Listando moradores da residência com ID: " + residenciaId);
        return pessoaDAO.listarMoradoresPorResidencia(residenciaId);
    }

    public boolean excluirMorador(int idMorador) throws SQLException {
        return pessoaDAO.excluirMorador(idMorador);
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) throws SQLException {
        logger.info("Obtendo proprietário da residência com ID: " + residenciaId);
        return pessoaDAO.obterProprietarioPorResidencia(residenciaId);
    }

}
