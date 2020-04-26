package com.ds.FireAlarmMonitor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.ds.FireAlarmMonitor.model.FireAlarm;
import com.ds.FireAlarmMonitor.util.DatabaseConnection;

import java.sql.*;

public class FireAlarmDao {

	
	private static Connection con;
	
	//Fetching all the data from the firealarm table
	public  List<FireAlarm> getFireAlarms(){
		
		String sql = "select * from firealarm";
		ArrayList<FireAlarm>fireAlarmList  = new ArrayList<FireAlarm>();
		
		try {
			con = com.ds.FireAlarmMonitor.util.DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				FireAlarm fireAlarm = new FireAlarm();
				fireAlarm.setId(rs.getInt(1));
				fireAlarm.setRoomNo(rs.getInt(2));
				fireAlarm.setFloorNo(rs.getInt(3));
				
				fireAlarm.setStatus(rs.getInt(4));
				
				fireAlarm.setSmokeLevel(rs.getInt(5));
				fireAlarm.setCo2Level(rs.getInt(6));
				
				System.out.println(fireAlarm);
				fireAlarmList.add(fireAlarm);

			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return fireAlarmList;
	}
	
	/*
	 ** THis method is to fetch a specific fire alarm from the db  
	 */
	public FireAlarm getAlarm(int id) {
		String sql = "Select * from firealarm where id="+id;
		FireAlarm fireAlarm = new FireAlarm();
		ArrayList<FireAlarm>fireAlarmList  = new ArrayList<FireAlarm>();
		
try {
			con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				
				fireAlarm.setId(rs.getInt(1));
				fireAlarm.setRoomNo(rs.getInt(2));
				fireAlarm.setFloorNo(rs.getInt(3));
				fireAlarm.setStatus(rs.getInt(4));
				fireAlarm.setSmokeLevel(rs.getInt(5));
				fireAlarm.setCo2Level(rs.getInt(6));
				
			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return fireAlarm;
		
	}
	
	/*
	 * THis method is to add new fire Alaarm to the database
	 */
	public void addAlarm(FireAlarm fireAlarm) {
		String sql = "insert into firealarm(roomNo,floorNo) values(?,?)";
	
try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(1, fireAlarm.getRoomNo());
			pStatement.setInt(2, fireAlarm.getFloorNo());
			
			pStatement.executeUpdate();
			con.close();
		}catch (Exception e) {
			System.out.println(e);
	}
}

	/*
	 * * This method is to update sensor records of a specific fire alarm of the database
	 */
	public void updateRecords(FireAlarm fireAlarm, int id) {
		String sql = "update firealarm set status = ?, smokeLevel =?, co2Level =? where id=?";
		
try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(4, id);
			pStatement.setInt(1, fireAlarm.getStatus());
			pStatement.setInt(2, fireAlarm.getSmokeLevel());
			pStatement.setInt(3, fireAlarm.getCo2Level());
			pStatement.executeUpdate();
			System.out.println("Updated successfully");
			
			con.close();
		}catch (Exception e) {
			System.out.println(e);
	}
	}
	
	/*
	 * * THis method is to update fire alarm details 
	 */
	public void updateAlarm(FireAlarm fireAlarm, int id) {
		String sql = "update firealarm set roomNo = ?, floorNo=? where id=?";
		
		try {
					con = DatabaseConnection.getConnection();
					PreparedStatement pStatement = con.prepareStatement(sql);
					pStatement.setInt(3, id);
					pStatement.setInt(1, fireAlarm.getRoomNo());
					pStatement.setInt(2, fireAlarm.getFloorNo());
					pStatement.executeUpdate();
					System.out.println("Updated successfully");
					
					con.close();
				}catch (Exception e) {
					System.out.println(e);
			}
	}
	
	/*
	 * this method is to delete a specific fire alarm from the database
	 */
	public void deleteAlarm( int id) {
		String sql = "DELETE from firealarm where id = ?";
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(1, id);
			
			pStatement.executeUpdate();
			System.out.println("Deleted successfully");
			
			con.close();
		}catch (Exception e) {
			System.out.println(e);
	}
	}
}