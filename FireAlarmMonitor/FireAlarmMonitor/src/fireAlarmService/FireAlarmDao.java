package fireAlarmService;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class FireAlarmDao {

	//private static ArrayList<FireAlarm>fireAlarmList  = new ArrayList<FireAlarm>();
	Connection con = null;
	
	//Building the connection with the database firealarmmonitor
	
	public FireAlarmDao() {
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
	}
	
	
	//Fetching all the data from the firealarm table
	public  List<FireAlarm> getFireAlarms(){
		String sql = "select * from firealarm";
		ArrayList<FireAlarm>fireAlarmList  = new ArrayList<FireAlarm>();
		
		try {
			
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
		
		}catch (Exception e) {
			System.out.println(e);
		}
		return fireAlarmList;
	}
	
	public FireAlarm getAlarm(int id) {
		String sql = "Select * from firealarm where id="+id;
		FireAlarm fireAlarm = new FireAlarm();
		ArrayList<FireAlarm>fireAlarmList  = new ArrayList<FireAlarm>();
		
try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				
				fireAlarm.setId(rs.getInt(1));
				fireAlarm.setRoomNo(rs.getInt(2));
				fireAlarm.setFloorNo(rs.getInt(3));
				fireAlarm.setStatus(rs.getInt(4));
				fireAlarm.setSmokeLevel(rs.getInt(5));
				fireAlarm.setCo2Level(rs.getInt(6));
				
				System.out.println(fireAlarm);
				fireAlarmList.add(fireAlarm);

			}
		
		}catch (Exception e) {
			System.out.println(e);
		}
		return fireAlarm;
		
	}
	
	
	public void addAlarm(FireAlarm fireAlarm) {
		String sql = "insert into firealarm(roomNo,floorNo) values(?,?)";
	
try {
			
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(1, fireAlarm.getRoomNo());
			pStatement.setInt(2, fireAlarm.getFloorNo());
			

			
			pStatement.executeUpdate();
			
		}catch (Exception e) {
			System.out.println(e);
	}
}

	public void updateRecords(FireAlarm fireAlarm, int id) {
		String sql = "update firealarm set status = 1, smokeLevel =?, co2Level =? where id=?";
		
try {
			
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(3, id);
			pStatement.setInt(1, fireAlarm.getSmokeLevel());
			pStatement.setInt(2, fireAlarm.getCo2Level());
			pStatement.executeUpdate();
			System.out.println("Updated successfully");
		}catch (Exception e) {
			System.out.println(e);
	}
	}
	
	public void updateAlarm(FireAlarm fireAlarm, int id) {
		String sql = "update firealarm set roomNo = ?, floorNo=? where id=?";
		
		try {
					
					PreparedStatement pStatement = con.prepareStatement(sql);
					pStatement.setInt(3, id);
					pStatement.setInt(1, fireAlarm.getRoomNo());
					pStatement.setInt(2, fireAlarm.getFloorNo());
					pStatement.executeUpdate();
					System.out.println("Updated successfully");
				}catch (Exception e) {
					System.out.println(e);
			}
	}
	
	public void deleteAlarm( int id) {
		String sql = "DELETE from firealarm where id = ?";
		
		try {
			
			PreparedStatement pStatement = con.prepareStatement(sql);
			pStatement.setInt(1, id);
			
			pStatement.executeUpdate();
			System.out.println("Deleted successfully");
		}catch (Exception e) {
			System.out.println(e);
	}
	}
}