package com.josias.gestorcondominio;

import com.josias.gestorcondominio.model.GestorDAO;
import java.sql.Connection;
import java.sql.SQLException;

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

    }
}
