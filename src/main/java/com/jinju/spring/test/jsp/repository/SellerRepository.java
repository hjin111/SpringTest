package com.jinju.spring.test.jsp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.spring.test.jsp.domain.Seller;

@Mapper
public interface SellerRepository {
	
	// nickname, temperature, profileImage 전달 받아서 insert 쿼리를 구성 하고 insert 쿼리를 수행 하고 그 결과를 돌려주는 기능 만들기
	// @Param() 어노테이션 붙여서 실제 xml에서 사용될 키워드 입력
	// 전달 받은 값이 xml 에서 쿼리를 완성 시키는데 쓰도록 하기 위해 해당하는 값에 매칭이 될 키워드를 @Param() 어노테이션을 통해 지정을 해 주는 것이다.
	public int insertSeller(
			@Param("nickname") String nickname
			, @Param("temperature") double temperature
			, @Param("profileImage") String profileImage);
	
	// 가장 최근에 등록한 한 행의 정보를 조회
	// 한 행의 정보는 테이블의 컬럼 이름과 정확히 일치 하는 멤버 변수를 가진 Entity 클래스를 만들어서 그 객체 활용
	public Seller selectLastSeller();
}
