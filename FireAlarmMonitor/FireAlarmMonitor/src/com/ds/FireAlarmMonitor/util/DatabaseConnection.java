package com.ds.FireAlarmMonitor.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	/*
	 * * This method is to build the connection with the database
	 */
	public static Connection getConnection() {
		Connection con = null;
		
		String url = "jdbc:mysql://localhost:3306/firealarmmonitor";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Connected successfully");
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return con;
	}

}
