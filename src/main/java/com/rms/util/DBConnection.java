package com.rms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties");
                    
            props.load(input);

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("✅ DB Properties Loaded");

        } catch (Exception e) {
            System.out.println("❌ DB INIT ERROR");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("✅ DB Connected Successfully");
            return con;
        } catch (Exception e) {
            System.out.println("❌ DB CONNECTION FAILED");
            e.printStackTrace();
            throw e;
        }
    }
}