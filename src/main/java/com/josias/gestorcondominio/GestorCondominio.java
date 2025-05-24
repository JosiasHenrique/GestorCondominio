package com.josias.gestorcondominio;

import com.josias.gestorcondominio.controller.MesDevidoController;
import com.josias.gestorcondominio.model.MesDevido;
import com.josias.gestorcondominio.model.Residencia;

import java.sql.SQLException;
import java.util.List;
public class GestorCondominio {

    public static void main(String[] args) throws SQLException {
        
        MesDevidoController mdc = new MesDevidoController();
        Residencia r = new Residencia();
        r.setId(1);
        MesDevido mv = new MesDevido(3, 2025, 1000, r);
        //mdc.inserirMesDevido(mv);
        //mdc.excluirMesDevido(9);
        
        List<MesDevido> meses = mdc.listarMesesDevidosPorResidencia(1);
        
         double total = 0.0;
        
        for(MesDevido x: meses) {
            total += x.getValor();
            System.out.println("Mes: " + x.getMes());
            System.out.println("Ano: " + x.getAno());
            System.out.println("Valor: " + x.getValor());
        }
        
        System.out.println("Total: R$" + total);
    }
}
