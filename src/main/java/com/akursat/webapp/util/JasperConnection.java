/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author akursat
 */
public class JasperConnection {
    
    public Connection getConn(){
       try
		{
		String url = "jdbc:mysql://localhost:3306/db1";
		String user = "root";
		String password = "akursat";

		// Load the Connector/J driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Establish connection to MySQL
		Connection conn = DriverManager.getConnection(url, user, password);
                
               
                return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
       return null;
    }
                
}
