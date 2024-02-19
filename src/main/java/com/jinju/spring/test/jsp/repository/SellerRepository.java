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
	
	public Seller selectSeller(@Param("id") int id); // 이 id 전달 받아서 수행 할 쿼리 안에 들어가야 된다. xml 에서 작성 될 코드에 자바 변수가 그대로 활용 될 수 없기 때문에 
									   // 해당하는 변수가 어떤 키워드로 xml 에서 사용될지를 @Param 어노테이션으로 지칭 해주면 됨 어떤 값이 들어가든 키워드 가지고 xml 에서 쓰면 됨 
									   // id 로 전달된 값이 @Param 어노테이션 안에 있는 이름으로 해당 select 쿼리 안에 수행 됨 
	
	// 몇 행이 조회되느냐에 따라서 리턴 타입이 달라져야 한다.
	// 정확하게 딱 한 행이 조회되거나 아니거나일 경우에는 클래스 이름으로 
	// 그렇지 않을 경우 한 행일수도 있고 10개 일수도 있고 5개 일수도 있고 0개일수도 있을 땐 List 이다.
	// 지금은 딱 정확히 한 행 혹은 아예 조회가 안되는 경우 둘 중에 하나의 상태이기 때문에 List일 필요는 없다.
	
	// 메소드 호출하면서 id 값 전달하면 select 쿼리 수행 되고 조회된 한 행의 결과가 해당 하는 Entity 클래스의 객체를 mybatis가 직접 생성해서 멤버 변수에 그 클래스 안에 있는 setter를 통해 값을 하나하나 채워 넣고 그 결과를 리턴해줄거임  
}
