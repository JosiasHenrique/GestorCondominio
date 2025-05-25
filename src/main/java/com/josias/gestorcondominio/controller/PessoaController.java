package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaController {

    private final PessoaDAO pessoaDAO;
    private static final Logger logger = Logger.getLogger(PessoaController.class.getName());

    public PessoaController() {
        this.pessoaDAO = new PessoaDAOImpl();
    }

    public boolean inserirProprietario(Proprietario p) {

        if (p == null || p.getNome() == null || p.getNome().trim().isEmpty()
                || p.getCpf() == null || p.getCpf().trim().isEmpty()
                || p.getRg() == null || p.getRg().trim().isEmpty()) {

            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg).");
            return false;
        }

        if (p.getIdade() < 18) {
            logger.log(Level.WARNING, "Idade mínima é 18 anos. IDade informada: {0}", p.getIdade());
            return false;
        }
        return pessoaDAO.inserirProprietario(p);
    }

    public boolean atualizarProprietario(Proprietario p) {
        if (p == null || p.getNome() == null || p.getNome().trim().isEmpty() || p.getCpf() == null
                || p.getCpf().trim().isEmpty() || p.getRg() == null || p.getRg().trim().isEmpty()) {                 // idade não pode ser nula

            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg).");
            return false;
        }

        if (p.getIdade() < 18) {
            logger.log(Level.WARNING, "Idade mínima é 18 anos. Idade informada: {0}", p.getIdade());
            return false;
        }

        return pessoaDAO.atualizarProprietario(p);
    }

    public boolean excluirProprietario(int idProprietario) {

        if (idProprietario <= 0) {
            logger.log(Level.WARNING, "ID de proprietário inválido: {0}", idProprietario);
            return false;
        }
        return pessoaDAO.excluirProprietario(idProprietario);
    }

    public boolean inserirMorador(Morador m, int residenciaId) {
        if (m == null || m.getNome() == null || m.getNome().trim().isEmpty()
                || m.getCpf() == null || m.getCpf().trim().isEmpty()
                || m.getRg() == null || m.getRg().trim().isEmpty()
                || residenciaId <= 0) {

            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg) ou residência inválida.");
            return false;
        }
        return pessoaDAO.inserirMorador(m, residenciaId);
    }

    public boolean atualizarMorador(Morador m) {
        if (m == null || m.getNome() == null || m.getNome().trim().isEmpty()
                || m.getCpf() == null || m.getCpf().trim().isEmpty()
                || m.getRg() == null || m.getRg().trim().isEmpty()) {
            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg) ou residência inválida.");
            return false;
        }
        return pessoaDAO.atualizarMorador(m);
    }

    public List<Morador> listarMoradoresPorResidencia(int residenciaId) {
        if (residenciaId <= 0) {
            logger.log(Level.WARNING, "ID da residência inválido: {0}", residenciaId);
            return Collections.emptyList();
        }
        return pessoaDAO.listarMoradoresPorResidencia(residenciaId);
    }

    public boolean excluirMorador(int idMorador) {

        if (idMorador <= 0) {
            logger.log(Level.WARNING, "ID de proprietário inválido: {0}", idMorador);
            return false;
        }
        return pessoaDAO.excluirMorador(idMorador);
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) {
        if (residenciaId <= 0) {
            logger.log(Level.WARNING, "ID de residência inválido: {0}", residenciaId);
            return null;
        }
        return pessoaDAO.obterProprietarioPorResidencia(residenciaId);
    }

}
