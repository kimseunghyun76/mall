package com.springapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB 
{
    private static ConnectionDB instance = new ConnectionDB();
    
    String url = "jdbc:mysql://localhost/testdb";
    String user = "tester";
    String password = "1234";
    String driverClass = "com.mysql.jdbc.Driver"; 
     
    private ConnectionDB() 
    {
        try 
        {
            Class.forName(driverClass);
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
     
    public static ConnectionDB getInstance()
    {
        return instance;
    }
     
    public Connection getConnection() throws SQLException, ClassNotFoundException 
    {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }   
}