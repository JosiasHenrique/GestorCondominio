package com.josias.gestorcondominio.controller;
import com.josias.gestorcondominio.dao.PessoaDAO;
import com.josias.gestorcondominio.dao.PessoaDAOImpl;
import com.josias.gestorcondominio.model.Proprietario;
import java.sql.SQLException;


public class PessoaController {

    private final PessoaDAO pessoaDAO;

    public PessoaController() { 
        this.pessoaDAO = new PessoaDAOImpl();
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

    public void excluirMorador(int idMorador) throws SQLException {
        pessoaDAO.excluirMorador(idMorador);
    }

}
