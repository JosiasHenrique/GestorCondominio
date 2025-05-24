package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import java.sql.SQLException;

public interface PessoaDAO {
  
    void inserirProprietario(Proprietario proprietario) throws SQLException;
    void inserirMorador(Morador morador, int residenciaId) throws SQLException;
    void atualizarProprietario(Proprietario proprietario) throws SQLException;
    void atualizarMorador(Morador morador) throws SQLException;
    void excluirMorador(int idMorador) throws SQLException;
    void excluirProprietario(int idProprietario) throws SQLException;
}
