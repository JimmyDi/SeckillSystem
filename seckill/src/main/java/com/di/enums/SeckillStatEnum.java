package com.di.enums;

public enum SeckillStatEnum{
	SUCCESS(1,"Succeed"),
	END(0,"Over"),
	REPEAT_KILL(-1,"RepeatSeckill"),
	INNER_ERROR(-2,"System Error"),
	DATE_REWRITE(-3,"Data Rewrite");
	
	private int state;
	private String info;
	
	SeckillStatEnum(int state, String info){
		this.state = state;
		this.info = info;
	}
	
	public int getState(){
		return state;
	}
	
	public String getInfo(){
		return info;
	}
	
	public static SeckillStatEnum stateof(int index)
	{
		for(SeckillStatEnum state:values())
		{
			if(state.getState()==index)
			{
				return state;
			}
		}
		return null;
	}
}