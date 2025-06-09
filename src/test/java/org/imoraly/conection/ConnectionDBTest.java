package org.imoraly.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class ConnectionDBTest {

    public static Connection createTableFreelancer() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement statement = conn.createStatement();
        statement.execute(""" 
                create table if not exists freelancer (
                    id integer primary key autoincrement,
                    name varchar(255) not null,
                    cpf varchar(12) not null unique,
                    email text,
                    specialty text
                );
        """);
        return conn;
    }

    public static Connection createTableClient() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement statement = conn.createStatement();
        statement.execute(""" 
                CREATE TABLE IF NOT EXISTS client (
                    id INTEGER PRIMARY key autoincrement,
                    name TEXT NOT NULL,
                    cnpj_cpf TEXT NOT NULL UNIQUE,
                    email TEXT,
                    telephone TEXT
                );
        """);
        return conn;
    }

    public static Connection createTableContract() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement statement = conn.createStatement();
        statement.execute(""" 
                CREATE TABLE IF NOT EXISTS contract (
                    id INTEGER PRIMARY key autoincrement,
                    description TEXT NOT NULL,
                    hourly_rate REAL NOT NULL,
                    contracted_hours INTEGER NOT NULL,
                    tax REAL DEFAULT 0.0,
                    bonus REAL DEFAULT 0.0,
                    status TEXT NOT NULL,
                    id_freelancer INTEGER NOT NULL,
                    id_client INTEGER NOT NULL,
                    FOREIGN KEY (id_freelancer) REFERENCES freelancer(id) ON DELETE CASCADE,
                    FOREIGN KEY (id_client) REFERENCES client(id) ON DELETE CASCADE
                );
        """);
        return conn;
    }
}