package com.lavanderia.utp.utils;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/lavanderia_utp", "postgres", "lavanderia");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return cn;
    }   
}
