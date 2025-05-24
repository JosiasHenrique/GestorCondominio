package com.josias.gestorcondominio;

import com.josias.gestorcondominio.controller.PessoaController;
import com.josias.gestorcondominio.controller.ResidenciaController;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.model.Residencia;
import java.sql.SQLException;
import java.util.List;
public class GestorCondominio {

    public static void main(String[] args) throws SQLException {
        
        PessoaController pc = new PessoaController();
        ResidenciaController rc = new ResidenciaController();
        
        List<Residencia> residencias = rc.listarResidencias();
        
        System.out.println("Residencias: \n");
        
        for(Residencia r: residencias) {
            System.out.println("Residencia: " + r.getId());
            
            System.out.println("Proprietario: " + pc.obterProprietarioPorResidencia(r.getId()).getNome());
            System.out.println("Moradores: \n");
            for(Morador m : pc.listarMoradoresPorResidencia(r.getId())) {
                System.out.println("Morador: " + m.getId());
                System.out.println("Nome: " + m.getNome());
            }
        }
        
    }
}
