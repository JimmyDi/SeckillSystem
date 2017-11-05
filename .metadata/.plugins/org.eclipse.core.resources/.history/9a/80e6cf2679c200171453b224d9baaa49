package com.di.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.di.entity.Seckill;

public interface SeckillDao
{
	//decrease number of products in stock
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	//search product by Id
	Seckill queryById(long seckillId);
	//search list of products
	List<Seckill> queryAll(@Param("offset") int off,@Param("limit") int limit);
}