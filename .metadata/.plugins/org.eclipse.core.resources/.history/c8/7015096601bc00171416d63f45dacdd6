package com.soecode.lyf.dao;
import org.apache.ibatis.annotations.Param;
import com.soecode.lyf.entity.Appointment;

public interface AppointmentDao{
	
	int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);
	
	Appointment queryByKeyWithBook(@Param("bookId") long bookId, @Param("studentId")long studentId);
}