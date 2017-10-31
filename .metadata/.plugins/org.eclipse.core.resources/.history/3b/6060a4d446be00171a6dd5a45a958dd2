package com.di.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.di.entity.Book;

public interface BookDao{
	Book queryById(long id);
	List<Book> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	int reduceNumber(long bookId);
}