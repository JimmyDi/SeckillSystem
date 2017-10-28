package com.di.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.di.dao.AppointmentDao;
import com.di.dao.BookDao;
import com.di.dto.AppointExecution;
import com.di.entity.Appointment;
import com.di.entity.Book;
import com.di.enums.AppointStateEnum;
import com.di.exception.AppointException;
import com.di.exception.NoNumberException;
import com.di.exception.RepeatAppointException;
import com.di.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BookDao bookDao;

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Override
	public Book getById(long bookId){
		return bookDao.queryById(bookId);
	}
	@Override
	public List<Book> getList(){
		return bookDao.queryAll(0,1000);
	}
	
	@Override
	@Transactional
	public AppointExecution appoint(long bookId,long studentId){
		try{
			int update = bookDao.reduceNumber(bookId);
			if(update <=0){
				throw new NoNumberException("no number");
			}else{
				int insert = appointmentDao.insertAppointment(bookId, studentId);
				if(insert <= 0){
					throw new RepeatAppointException("repeat appoint");
				}else{
					Appointment appointment=appointmentDao.queryByKeyWithBook(bookId,studentId);
					return new AppointExecution(bookId,AppointStateEnum.SUCCESS,appointment);
				}
			}
		}catch(NoNumberException e1){
			throw e1;
		}catch(RepeatAppointException e2){
			throw e2;
		}catch (Exception e){
			logger.error(e.getMessage(),e);
		throw new AppointException("appoint inner error:" + e.getMessage());	
		}
		}
}