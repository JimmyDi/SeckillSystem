package com.di.dto;

import com.di.entity.Appointment;
import com.di.enums.AppointStateEnum;

public class AppointExecution{
	
	private long bookId;
	private int state;
	private String stateInfo;
	private Appointment appointment;
	
	//no parameters constructor
	public AppointExecution(){
	}
	
	//failing constructor
	public AppointExecution(long bookId, AppointStateEnum stateEnum){
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//succeed constructor
	public AppointExecution(long bookId, AppointStateEnum stateEnum,Appointment appointment){
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.appointment = appointment;
	}
	
	//setter and getter method
	public void setBookId(long bookId){
		this.bookId = bookId;
	}
	public long getBookId(){
		return this.bookId;
	}
	
	public void setState(int state){
		this.state = state;
	}
	public int getState(){
		return this.state;
	}
	
	public void setStateInfo(String stateInfo){
		this.stateInfo = stateInfo;
	}
	public String getStateInfo(){
		return this.stateInfo;
	}
	
	public void setAppointment(Appointment appointment){
		this.appointment = appointment;
	}
	public Appointment getAppointment(){
		return appointment;
	}
	
	@Override
	public String toString(){
		return "bookId: "+bookId+",state: "+state+",stateInfo: "+stateInfo+",appointment: "+appointment;
	}
}