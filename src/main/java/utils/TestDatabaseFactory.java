package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseFactory {
    public static Connection createAllTable() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement statement = conn.createStatement();
        statement.execute("""
            CREATE TABLE IF NOT EXISTS freelancer (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name VARCHAR(255) NOT NULL,
                cpf VARCHAR(12) NOT NULL UNIQUE,
                email TEXT,
                specialty TEXT
            );
        """);

        statement.execute("""
            CREATE TABLE IF NOT EXISTS client (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                cnpj_cpf TEXT NOT NULL UNIQUE,
                email TEXT,
                telephone TEXT
            );
        """);

        statement.execute("""
            CREATE TABLE IF NOT EXISTS contract (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
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

        statement.execute("INSERT INTO freelancer (name, cpf, email, specialty) VALUES ('Maria', '122234567489', 'maria@gmail.com', 'Front end');");
        statement.execute("INSERT INTO client (name, cnpj_cpf, email, telephone) VALUES ('João', '12345678000199', 'joao@gmail.com', '12345678999');");
        statement.execute("""
            INSERT INTO contract (description, hourly_rate, contracted_hours, tax, bonus, status, id_freelancer, id_client) 
            VALUES ('Projeto Teste', 20.0, 10, 0.0, 0.0, 'Pendente', 1, 1);
        """);

        return conn;
    }
}
