package com.jinju.spring.test.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.spring.test.mybatis.domain.RealEstate;

@Mapper // xml이랑 매핑 해서 사용할거기 때문에 그걸 처리해도록 하는 어노테이션 mapper 어노테이션을 붙여주기 
public interface RealEstateRepository {

	// id와 일치하는 한 행의 정보를 리턴해주는 기능을 만들어야 됨
	public RealEstate selectRealEstate(@Param("id") int id);
	// 해당 하는 인터페이스이기 때매 메소드 정의만 하면 끝
	// 우리가 만들 메소드는 호출 됐을 떄 실제로 기능이 수행이 되야 함
	// 원래 자바 문법 대로 라면 해당하는 인터페이스를 implements 하는 클래스를 만들어서 그 안에서 구현을 해야 되는데
	// 우리는 mybatis의 도움을 받아서 이 메소드가 호출 되었을 때 실행 될 쿼리를 정돈해볼거임
	// 해당하는 메소드가 호출이 되었을 때 실행 될 쿼리를 구현 할 mapper.xml 만들기
	// id 라는 이 변수 값이 어떤 키워드로 사용하도록 할건지를 매칭 시켜 줘야 함 => @Param("") 어노테이션을 통해 매칭 시켜 주면 됨
	// "" 안에 들어갈 값이 mapper xml에서 사용할 키워드이다. id 라는 변수 값을 사용할 키워드를 ""안에 있는 값으로 지정해준다.
	
	// 한 행 정보를 저장 하기 위해서 만들어 놓은 Entity 클래스를 제너릭으로 세팅
	public List<RealEstate>selectRealEstateListByRentPrice(@Param("rent") int rentPrice);

	// area 와 price 이 값은 어디에 사용된다? 쿼리를 완성시키는데 사용된다. 그러기 위해서는 해당하는 자바 변수를 xml에서 사용할 키워드로 매칭시켜 줘야 되고 그걸 위한 어노테이션이 @Param 어노테이션이다.  
	public List<RealEstate> selectRealEstateListByAreaAndPrice(
			@Param("area") int area
			,@Param("price") int price);
}
