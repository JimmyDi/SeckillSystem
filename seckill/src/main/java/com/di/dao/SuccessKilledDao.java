package com.di.dao;

import org.apache.ibatis.annotations.Param;

import com.di.entity.SuccessKilled;

public interface SuccessKilledDao{
	//insert details of buying
	int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
	//get Successkilled object by product id
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}