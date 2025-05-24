package com.josias.gestorcondominio;

import com.josias.gestorcondominio.controller.PessoaController;
import com.josias.gestorcondominio.model.Proprietario;
import java.sql.SQLException;

public class GestorCondominio {

    public static void main(String[] args) throws SQLException {

        PessoaController pc = new PessoaController();
        
        Proprietario p = new Proprietario(20, "Talia Silva", "4565454", "8778685665");
        
        p.setId(12);
        
        //pc.inserirProprietario(p);
        
        //pc.excluirProprietario(13);
        
        //pc.atualizarProprietario(p);
      
    }
}
