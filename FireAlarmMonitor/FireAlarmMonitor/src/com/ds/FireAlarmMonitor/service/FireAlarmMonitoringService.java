package com.ds.FireAlarmMonitor.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ds.FireAlarmMonitor.controller.FireAlarmDao;
import com.ds.FireAlarmMonitor.controller.MailController;
import com.ds.FireAlarmMonitor.controller.SmsSender;
import com.ds.FireAlarmMonitor.controller.UserDao;
import com.ds.FireAlarmMonitor.model.FireAlarm;
import com.ds.FireAlarmMonitor.model.User;



@Path("/fireAlarms")
public class FireAlarmMonitoringService {
	List<FireAlarm> fireAlarms;
	FireAlarmDao alarmDao = new FireAlarmDao();
	UserDao userDao = new UserDao();
	
	public FireAlarmMonitoringService() {
		fireAlarms = alarmDao.getFireAlarms();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  List<FireAlarm> getFireAlarmList(){

		/*
		 * This method returns all the fire alarm details as JSON objects
		 */

		return fireAlarms;
		
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FireAlarm getFireAlarm(@PathParam("id") int id) {
		/*
		 * This method return the specific user to the provided id
		 */
		 return alarmDao.getAlarm(id);
	}
	
	@POST
	@Path("addAlarm")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFireAlarm(FireAlarm fireAlarm) {
		
		alarmDao.addAlarm(fireAlarm);
		System.out.println("Fire Alarm Added Successfully");
	}
	
	@PUT
	@Path("updateRecords/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFireAlarmRecords(@PathParam("id") int id,FireAlarm fireAlarm) {
		alarmDao.updateRecords(fireAlarm, id);
		
	}
	
	@PUT
	@Path("updateFireAlarm/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFireAlarm(@PathParam("id")int id, FireAlarm fireAlarm) {
		if(alarmDao.getAlarm(id).getId() == 0) {
			alarmDao.addAlarm(fireAlarm);
		}
		
		else {
			alarmDao.updateAlarm(fireAlarm, id);
		}
		
	}
	
	@DELETE
	@Path("deleteFireAlarm/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteFireAlarm(@PathParam("id")int id) {
		 FireAlarm a = alarmDao.getAlarm(id);
		 
		 if(a.getId() != 0) {
			 alarmDao.deleteAlarm(id);
		 }
		 
		 else {
			 System.out.println("Unable to find the alarm with the id "+id);
		 }
		
	}
	
	@POST
	@Path("/sendMail")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMail(FireAlarm fireAlarm) {
	
		 MailController mail = new MailController();
		
		try {
			mail.sendMail(fireAlarm);
			System.out.println("Mail sent successfully");
		} catch (Exception e) {
			System.out.println("Oops! fail sending email");
			e.printStackTrace();
		}
	}

	@POST
	@Path("/sendSMS")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendSMS(FireAlarm fireAlarm) {
		SmsSender sms = new SmsSender();
		try {
			sms.sendSMS(fireAlarm);
			System.out.println("sms sent sucessfully");
		}catch (Exception e){
			System.out.println("sms service crashed");
		}
		
	}
	
	@POST
	@Path("addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(User user) {
		userDao.addUser(user);
		System.out.println("New User Added Successfully");
	}
	
	
	@GET
	@Path("getUser/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("email") String email) {
		
		 return userDao.getUser(email);
	}
	
}
