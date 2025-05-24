package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import java.sql.SQLException;
import java.util.List;

public interface PessoaDAO {
  
    boolean inserirProprietario(Proprietario proprietario) throws SQLException;
    boolean inserirMorador(Morador morador, int residenciaId) throws SQLException;
    boolean atualizarProprietario(Proprietario proprietario) throws SQLException;
    boolean atualizarMorador(Morador morador) throws SQLException;
    boolean excluirMorador(int idMorador) throws SQLException;
    boolean excluirProprietario(int idProprietario) throws SQLException;
    List<Morador> listarMoradoresPorResidencia(int residenciaId) throws SQLException;
    Proprietario obterProprietarioPorResidencia(int residenciaId) throws SQLException;
      
}
