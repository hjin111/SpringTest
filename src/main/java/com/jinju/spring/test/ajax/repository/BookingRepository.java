package com.jinju.spring.test.ajax.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.spring.test.ajax.domain.Booking;

@Mapper
public interface BookingRepository {
	
	public List<Booking> selectBookingList();
	
	public int insertBooking(
			@Param("name") String name
			, @Param("date") Date date
			, @Param("day") int day
			, @Param("headcount") int headcount
			, @Param("phoneNumber") String phoneNumber
			, @Param("state") String state);
	
	public int deleteBooking(@Param("id") int id);
	
	// 전달할 이름과 전화번호가 일치하는 행은 딱 하나 혹은 없는 경우, 둘 중에 하나라는 가정을 가지고 간다
	// 일치하면 딱 하나만 일치하고 일치하지 않으면 아예 일치하지 않는다. 이 형태의 가정으로 갈거라 리턴 타입은 하나의 행을 저장하는 Entity 클래스로 지정
	public Booking selectBooking(
			@Param("name") String name
			, @Param("phoneNumber") String phoneNumber); // 여기서 전달할 파라미터는 select 쿼리를 수행 할 때 쓸라고 추가한거임
	                                                     // select 쿼리 안에 이 파라미터 값들이 조건으로 활용하려면 xml 에서 활용이 되어야 하고
	                                                     // 그러기 위해선 Param 어노테이션을 통해 키워드를 매칭 시켜 준다.

}