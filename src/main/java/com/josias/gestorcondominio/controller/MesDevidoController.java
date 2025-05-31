package com.josias.gestorcondominio.controller;

import com.josias.gestorcondominio.dao.MesDevidoDAO;
import com.josias.gestorcondominio.dao.MesDevidoDAOImpl;
import com.josias.gestorcondominio.model.MesDevido;
import com.josias.gestorcondominio.observer.Observer;
import com.josias.gestorcondominio.observer.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MesDevidoController implements Subject {

    private static MesDevidoController instancia;
    private final List<Observer> observers = new ArrayList<>();
    private final MesDevidoDAO mesDevidoDAO;
    private static final Logger logger = Logger.getLogger(MesDevidoController.class.getName());

    private MesDevidoController() {
        this.mesDevidoDAO = new MesDevidoDAOImpl();
    }

    public static MesDevidoController getInstancia() {
        if (instancia == null) {
            instancia = new MesDevidoController();
        }
        return instancia;
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

        boolean sucesso = mesDevidoDAO.inserirMesDevido(mesDevido);
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;

    }

    public boolean excluirMesDevido(int id) {

        if (id <= 0) {
            logger.log(Level.WARNING, "Tentativa de excluir MesDevido com ID inválido: {0}", id);
            return false;
        }
        
        boolean sucesso = mesDevidoDAO.excluirMesDevido(id);
        
        if (sucesso) {
            notificarObservers();
        }
        return sucesso;
    }

    public List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId) {

        if (residenciaId <= 0) {
            logger.log(Level.WARNING, "ID de residência inválido: {0}", residenciaId);
            return Collections.emptyList();
        }

        List<MesDevido> mesesDevidos = mesDevidoDAO.listarMesesDevidosPorResidencia(residenciaId);
        return mesesDevidos;
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
