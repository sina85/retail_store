package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    public static Connection getConnection()
    {
         Connection con=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/retail_store", "root", "234365");
           //System.out.println("CONGRATULATIONS DATABAE Succefully Connected");
          
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
   return con;
    }
}
