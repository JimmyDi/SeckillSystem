package com.di.service;

import java.util.List;

import com.di.dto.Exposer;
import com.di.dto.SeckillExecution;
import com.di.entity.Seckill;
import com.di.exception.RepeatKillException;
import com.di.exception.SeckillCloseException;
import com.di.exception.SeckillException;

public interface SeckillService{
	
	List<Seckill> getSeckillList();
	
	Seckill getById(long seckillId);
	
	Exposer exportSeckillUrl(long seckillId);
	
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
		throws SeckillException,RepeatKillException,SeckillCloseException;
}