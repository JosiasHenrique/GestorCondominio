package com.josias.gestorcondominio;

import com.josias.gestorcondominio.model.GestorDAO;
import com.josias.gestorcondominio.model.MesDevido;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.model.Residencia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorCondominio {

    public static void main(String[] args) throws SQLException {

        GestorDAO dao = GestorDAO.getInstance();

        try {

            Connection conn = dao.getConnection();
            System.out.println("Conexão bem-sucedida!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Proprietario proprietario = new Proprietario(30, "João Francisco", "456565", "5465564656");
        Residencia residencia = new Residencia(520, "Rua dos Paulistas", "13880-000", null);
        
        dao.inserirResidencia(residencia, dao.inserirProprietario(proprietario));
        
        dao.inserirMorador(new Morador(null, 10, "joazinho", "545454", "54548777"), 1);

    }
}
