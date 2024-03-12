package com.jinju.spring.test.ajax.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.ajax.domain.Booking;
import com.jinju.spring.test.ajax.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public List<Booking> getBookingList() {
		List<Booking> bookingList = bookingRepository.selectBookingList();
		
		return bookingList;
	}
	
	public int addBooking(
			String name
			, Date date
			, int day
			, int headcount
			, String phoneNumber) {
		int count = bookingRepository.insertBooking(name, date, day, headcount, phoneNumber, "대기중");
		
		return count;
	}
	
	// delete, update, insert 는 기능 수행 이후에 대단하게 돌려줄 값이 없다. 그래서 mybatis 에서 리턴해주는 실행될 행의 갯수 정도면 충분하다
	public int deleteBooking(int id){
		
		int count = bookingRepository.deleteBooking(id);
		return count;
	}
	
	public Booking getBooking(String name, String phoneNumber) {
		
		Booking booking = bookingRepository.selectBooking(name, phoneNumber);
		
		return booking;
	}

}