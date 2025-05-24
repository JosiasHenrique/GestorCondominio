package com.josias.gestorcondominio;

import com.josias.gestorcondominio.controller.PessoaController;
import com.josias.gestorcondominio.model.Proprietario;
import java.sql.SQLException;

public class GestorCondominio {

    public static void main(String[] args) throws SQLException {

        PessoaController pc = new PessoaController();
        
        Proprietario p = new Proprietario(25, "João", "4565454", "8778685665");
        
        //pc.inserirProprietario(p);
        
        pc.excluirMorador(12);
      
    }
}
