package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Pessoa;
import com.josias.gestorcondominio.model.Proprietario;
import java.util.List;

public interface PessoaDAO {
  
    boolean inserirProprietario(Proprietario proprietario);
    boolean inserirMorador(Morador morador);
    boolean atualizarProprietario(Proprietario proprietario);
    boolean atualizarMorador(Morador morador);
    boolean excluirMorador(int idMorador);
    boolean excluirProprietario(int idProprietario);
    List<Morador> listarMoradoresPorResidencia(int residenciaId);
    List<Pessoa> listarPessoas(String tipo);
    Proprietario obterProprietarioPorResidencia(int residenciaId);
      
}
