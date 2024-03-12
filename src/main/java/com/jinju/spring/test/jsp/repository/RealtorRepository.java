package com.jinju.spring.test.jsp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.jinju.spring.test.jsp.domain.Realtor;

@Mapper
public interface RealtorRepository {
	
	// realtor 객체를 전달 받고 그 정보를 기반으로 realtor 테이블에 한 행을 insert 하는 쿼리를 수행시키는 메소드
	public int insertRealtor(Realtor realtor);
	
}
