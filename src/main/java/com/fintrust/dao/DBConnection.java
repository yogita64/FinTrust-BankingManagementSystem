package com.fintrust.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection conn = null;
	
	private static final String DBNAME = "mydb";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root123"; 
	
	
	
    // Get a single shared connection (basic implementation)
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                

                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println(" Database connected successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" Database connection failed!");
            }
        }
        return conn;
    }
    
    public static void main(String args[]) {
    	getConnection();
    }
}
