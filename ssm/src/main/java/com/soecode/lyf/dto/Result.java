package com.soecode.lyf.dto;

public class Result<T>{
	private boolean success;
	private T data;
	private String error;
	
	//constructor
	public Result(){
	}
	public Result(boolean success, T data){
		this.success = success;
		this.data = data;
	}
	public Result(boolean success, String error){
		this.success = success;
		this.error = error;
	}
	
	//setter and getter
	public void setSuccess(boolean success){
		this.success = success;
	}
	public boolean getSuccess(){
		return success;
	}
	
	public void setData(T data){
		this.data = data;
	}
	public T getData(){
		return this.data;
	}
	
	public void setError(String error){
		this.error = error;
	}
	public String getError(){
		return this.error;
	}
	
}