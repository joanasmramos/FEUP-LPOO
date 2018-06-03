package com.hatchrun.game.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Statement st;
    private Connection db;


    public DataBaseConnection() {
        driver = "org.postgresql.Driver";
        //url = "jdbc:postgresql://jqxhiedn:h60yKztJNkN57vdcFemHX3_E8sJjGLYJ@horton.elephantsql.com:5432/jqxhiedn";
        url = "postgres://jqxhiedn:h60yKztJNkN57vdcFemHX3_E8sJjGLYJ@horton.elephantsql.com:5432/jqxhiedn";
        username = "jqxhiedn";
        password = "h60yKztJNkN57vdcFemHX3_E8sJjGLYJ";

        registerDriver();
        connect();
        createStatement();
        closeConnection();
    }

    public void registerDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("1");
        }
    }

    public void connect() {
        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("2");
        }
    }

    public void createStatement() {
        try {
            st = db.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("3");
        }
    }

    public void closeConnection(){
        try {
            db.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
