package com.di.service;

import java.util.List;

import com.di.dto.AppointExecution;
import com.di.entity.Book;

public interface BookService{
	Book getById(long bookId);
	
	List<Book> getList();
	
	AppointExecution appoint(long bookId, long studentId);
}