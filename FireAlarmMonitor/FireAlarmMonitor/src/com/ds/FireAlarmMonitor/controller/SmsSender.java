package com.ds.FireAlarmMonitor.controller;

import com.ds.FireAlarmMonitor.model.FireAlarm;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



public class SmsSender {
 // Find your Account Sid and Auth Token at twilio.com/console
 public static final String ACCOUNT_SID =
         "ACae7f0f69b34d4c8724d932ff15b746d7";
 public static final String AUTH_TOKEN =
         "3fc0afae787af199383976d18075044f";

 public void sendSMS(FireAlarm sms) {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

     Message message = Message
             .creator(new PhoneNumber("+94710729569"), // to
                     new PhoneNumber("+18634171972"), // from
                     "Fire Alarm Monitor  Floor NO: " +sms.getFloorNo()+
                      "Room No: "+sms.getRoomNo()+" Ginnak Yako duwapan...!")
             .create();

     System.out.println(message.getSid());
 }
}

