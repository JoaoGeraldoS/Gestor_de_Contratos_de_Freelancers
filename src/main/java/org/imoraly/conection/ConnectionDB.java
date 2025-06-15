package org.imoraly.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static String url = "jdbc:sqlite:gestor.db";

    public static Connection connect() {

        try {

            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
