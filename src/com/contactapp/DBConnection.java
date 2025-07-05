package com.contactapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/contactdb";
            String user = "root"; // change this to your DB username
            String password = "root"; // change this to your DB password
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

