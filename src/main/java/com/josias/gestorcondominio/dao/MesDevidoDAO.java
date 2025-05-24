package com.josias.gestorcondominio.dao;

import com.josias.gestorcondominio.model.MesDevido;
import java.util.List;

public interface MesDevidoDAO {
    
    boolean inserirMesDevido(MesDevido mesDevido);
    boolean excluirMesDevido(int id);
    List<MesDevido> listarMesesDevidosPorResidencia(int residenciaId);
}
