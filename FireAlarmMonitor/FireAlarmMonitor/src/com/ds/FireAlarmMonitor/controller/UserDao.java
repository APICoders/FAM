package com.ds.FireAlarmMonitor.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ds.FireAlarmMonitor.model.User;
import com.ds.FireAlarmMonitor.util.DatabaseConnection;



public class UserDao {
	private static Connection con;
	
	/*
	 * *this method is to retrieve a specific user from User table of the database by providing email
	 */
	public User getUser(String email) {
		String sql = "Select * from user where email="+email;
		User user = new User();
		
		try {
			
			con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			/*
			 * set retrieved data to user object
			 */
			if(rs.next()) {
				user.setEmail(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
	
	/*
	 * *this method is to add new User to the database
	 */
	public void addUser(User user) {
		String sql = "insert into user(email,name,password) values(?,?,?)";
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setString(1, user.getEmail());
			pStatement.setString(2, user.getName());
			pStatement.setString(3, user.getPassword());
			
			pStatement.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
