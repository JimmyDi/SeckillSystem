package com.di.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.di.dao.SeckillDao;
import com.di.dao.SuccessKilledDao;
import com.di.dto.Exposer;
import com.di.dto.SeckillExecution;
import com.di.entity.Seckill;
import com.di.entity.SuccessKilled;
import com.di.enums.SeckillStatEnum;
import com.di.exception.RepeatKillException;
import com.di.exception.SeckillCloseException;
import com.di.exception.SeckillException;
import com.di.service.*;


@Service
public class SeckillServiceImpl implements SeckillService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//salt for encryption
	private final String salt = "shsdssljdd'l.";
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	public List<Seckill> getSeckillList(){
		return seckillDao.queryAll(0,4);
	}
	
	public Seckill getById(long seckillId){
		return seckillDao.queryById(seckillId);
	}
	
	public Exposer exportSeckillUrl(long seckillId){
		Seckill seckill = seckillDao.queryById(seckillId);
		if(seckill == null)//cannot find the detail of the prodect
		{
			return new Exposer(false,seckillId);
		}
		// seckill has not been launched yet
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		
		Date nowTime = new Date();
		if(startTime.getTime()>nowTime.getTime()||endTime.getTime()<nowTime.getTime())
		{
			return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		return new Exposer(true,md5,seckillId);	
	}
	
	private String getMD5(long seckillId)
	{
		String base = seckillId + "/" +salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Transactional
	public SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
		throws SeckillException, RepeatKillException, SeckillCloseException{
		if(md5 == null||!md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		Date nowTime = new Date();
		try{
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if(updateCount<=0)
			{
				//if no record is refreshed, the seckill has over
				throw new SeckillCloseException("seckill is closed");
			}else{
				//or refresh database, insert detail
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				//check whether this detail has been inserted , repeat seckill
				if(insertCount<=0)
				{
					throw new RepeatKillException("seckill repeated");
				}else{
					SuccessKilled successkilled  = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS,successkilled);
				}
			}
		}catch(SeckillCloseException e1)
		{
			throw e1;
		}catch(RepeatKillException e2)
		{
			throw e2;
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}
}







