package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.Residencia;
import java.util.List;

public interface ResidenciaDAO {
    
    boolean inserirResidencia(Residencia r);
    List<Residencia> findAllResidencias();
    boolean atualizarResidencia(Residencia r);
    boolean excluirResidencia(int idResidencia);
}
