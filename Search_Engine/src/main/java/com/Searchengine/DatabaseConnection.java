package com.Searchengine;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static Connection connection=null;
    public static Connection getConnection(){
        if(connection!=null)
            return connection;
        String db = ""; //add the name of your database
        String user = ""; //user name of database
        String pass = ""; //pass of database

        return getConnection(db,user,pass);
    }

    public static Connection getConnection(String db, String user, String pass){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pass);
        }catch (Exception E)
        {
            E.printStackTrace();
        }
        return connection;
    }

}
