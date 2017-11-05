package com.di.dto;

public class Exposer{
	
	//whether launch seckill
	private boolean exposed;
	
	//encryption
	private String md5;
	
	private long seckillId;
	
	private long now;
	private long start;
	private long end;
	
	
	//Constructor
	public Exposer(boolean exposed, String md5, long seckillId){
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}
	//false , seckill has not been launched, return
	public Exposer(boolean exposed, long seckillId,long now,long start, long end){
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	//false, cannot search the product, return
	public Exposer(boolean exposed, long seckillId){
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
	
	
	
	//setter getter
	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}	
	@Override
	public String toString(){
		return "Exposer: "+
				"exposed  "+exposed+
				"md5  "+md5+
				"seckillId  " + seckillId+
				"now  "+now+
				"start  "+start+
				"end  "+end;
	}
}