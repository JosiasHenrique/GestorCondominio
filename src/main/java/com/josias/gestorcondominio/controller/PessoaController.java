package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import java.util.List;

public class PessoaController {

    private final PessoaDAO pessoaDAO;

    public PessoaController() {
        this.pessoaDAO = new PessoaDAOImpl();
    }

    public boolean inserirProprietario(Proprietario p) {
        return pessoaDAO.inserirProprietario(p);
    }

    public boolean atualizarProprietario(Proprietario p) {
        return pessoaDAO.atualizarProprietario(p);
    }

    public boolean excluirProprietario(int idProprietario) {
        return pessoaDAO.excluirProprietario(idProprietario);
    }

    public boolean inserirMorador(Morador m, int residenciaId) {
        return pessoaDAO.inserirMorador(m, residenciaId);
    }

    public boolean atualizarMorador(Morador m) {
        return pessoaDAO.atualizarMorador(m);
    }

    public List<Morador> listarMoradoresPorResidencia(int residenciaId) {
        return pessoaDAO.listarMoradoresPorResidencia(residenciaId);
    }

    public boolean excluirMorador(int idMorador) {
        return pessoaDAO.excluirMorador(idMorador);
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) {
        return pessoaDAO.obterProprietarioPorResidencia(residenciaId);
    }

}
