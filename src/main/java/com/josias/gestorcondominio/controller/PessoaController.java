package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Pessoa;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.observer.Observer;
import com.josias.gestorcondominio.observer.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaController implements Subject {

    private static PessoaController instancia;
    private final List<Observer> observers = new ArrayList<>();
    private final PessoaDAO pessoaDAO;
    private static final Logger logger = Logger.getLogger(PessoaController.class.getName());

    private PessoaController() {
        this.pessoaDAO = new PessoaDAOImpl();
    }

    public static PessoaController getInstancia() {
        if (instancia == null) {
            instancia = new PessoaController();
        }
        return instancia;
    }

    public boolean inserirProprietario(Proprietario p) {

        if (p.getNome() == null || p.getNome().trim().isEmpty()
                || p.getCpf() == null || p.getCpf().trim().isEmpty()
                || p.getRg() == null || p.getRg().trim().isEmpty()) {

            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg).");
            return false;
        }

        if (p.getIdade() < 18) {
            logger.log(Level.WARNING, "Idade mínima é 18 anos. IDade informada: {0}", p.getIdade());
            return false;
        }

        boolean sucesso = pessoaDAO.inserirProprietario(p);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
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

        boolean sucesso = pessoaDAO.atualizarProprietario(p);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
    }

    public boolean excluirProprietario(int idProprietario) {

        if (idProprietario <= 0) {
            logger.log(Level.WARNING, "ID de proprietário inválido: {0}", idProprietario);
            return false;
        }

        boolean sucesso = pessoaDAO.excluirProprietario(idProprietario);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
    }

    public boolean inserirMorador(Morador m) {
        int residenciaId = m.getResidencia().getId();
        if (m.getNome() == null || m.getNome().trim().isEmpty()
                || m.getCpf() == null || m.getCpf().trim().isEmpty()
                || m.getRg() == null || m.getRg().trim().isEmpty()
                || residenciaId <= 0) {

            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg) ou residência inválida.");
            return false;
        }
        boolean sucesso = pessoaDAO.inserirMorador(m);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
    }

    public boolean atualizarMorador(Morador m) {
        if (m.getNome() == null || m.getNome().trim().isEmpty()
                || m.getCpf() == null || m.getCpf().trim().isEmpty()
                || m.getRg() == null || m.getRg().trim().isEmpty()) {
            logger.log(Level.WARNING, "Dados obrigatórios faltando (nome, cpf, rg) ou residência inválida.");
            return false;
        }
        boolean sucesso = pessoaDAO.atualizarMorador(m);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
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
        boolean sucesso = pessoaDAO.excluirMorador(idMorador);;
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
    }

    public Proprietario obterProprietarioPorResidencia(int residenciaId) {
        if (residenciaId <= 0) {
            logger.log(Level.WARNING, "ID de residência inválido: {0}", residenciaId);
            return null;
        }
        return pessoaDAO.obterProprietarioPorResidencia(residenciaId);
    }

    public List<Pessoa> listarPessoas(String tipo) {
        if (tipo == null || (!tipo.equalsIgnoreCase("Morador") && !tipo.equalsIgnoreCase("Proprietario"))) {
            logger.log(Level.WARNING, "Tipo inválido: {0}", tipo);
            return Collections.emptyList();
        }
        return pessoaDAO.listarPessoas(tipo);
    }

    @Override
    public void registrarObservador(Observer o) {
        observers.add(o);
    }

    @Override
    public void removerObservador(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer o : observers) {
            o.atualizar();
        }
    }

}
