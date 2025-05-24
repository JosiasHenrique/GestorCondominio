package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.dao.ResidenciaDAO;
import com.josias.gestorcondominio.dao.ResidenciaDAOImpl;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.model.Residencia;
import java.sql.SQLException;

public class PessoaController {

    private final PessoaDAO pessoaDAO;
    private final ResidenciaDAO residenciaDAO;

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

    public void excluirMorador(int idMorador) throws SQLException {
        pessoaDAO.excluirMorador(idMorador);
    }

}
