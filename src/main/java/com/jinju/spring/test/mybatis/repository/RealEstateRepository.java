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
	
	// realEstate 객체를 통해서 한 행을 저장 하는 기능, 한 행을 insert 하는 기능을 만들어야 함 그래야 service 에서 그 기능을 활요할 수 있음
	// insert 쿼리를 수행 하면 실행된 행의 갯수가 리턴 되기 때문에 그 사용법에 맞춰서 return 타입을 int로 지정 
	// 그리고 나서 파리미터로 전달 되는 realEstate 이 객체 안에 들어 있는 멤버 변수 값을 기반으로 insert 쿼리를 완성 시킬 거임
	// 그러면 객체를 기반으로 mapper.xml의 값을 사용할 때는 Param() 어노테이션 굳이 넣을 필요 없음 이거와 관련된 정보를 xml에 세팅해줄거기 때문이다.
	// 해당 하는 메소드를 호출했을 때 실행할 쿼리는 해당 하는 메소드와 연결될 xml 태그를 통해서 수행될거임.
	public int insertRealEstateByObject(RealEstate realEstate);
	// 위에 메소드를 만든 이유는 service 에서 객체 형태로 한 행을 insert 하기 위한 기능이 필요해서 만듬
	
	// 이 메소드를 통해서 수행 할 내용은 쿼리이다. 여기로 전달 된 파라미터들은 쿼리를 완성시키기 위해 필요한 데이터를 전달하는거고
	// 쿼리는 자바 코드가 아니라 xml을 통해서 만들어짐 그래서 변수 자체를 그대로 전달된다고 생각하지 말고 변수에 값을 사용하기 위한 키워드가 매칭이 되서 그 매칭된 키워드를 통해 쿼리가 만들어진다. 
	public int insertRealEstate(
			@Param("realtorId") int realtorId // Param 어노테이션을 통해 매칭 할 키워드를 넣어줘야 함
			, @Param("address") String address
			, @Param("area") int area
			, @Param("type") String type
			, @Param("price") int price
			, @Param("rentPrice") int rentPrice);
	
	// 전달 받은 값을 기반으로 쿼리를 만들기 위한거고 전달 받은 값은 xml에서 사용해야 하기 때문에
	// @Param 어노테이션을 통해 키워드로 매칭 시켜 주기
	public int updateRealEstate(
			@Param("id") int id // 같아야 될 이유는 없고 뜻이 같다 보니 키워드와 파라미터 변수 이름이 같아질 가능성이 높을 뿐
			, @Param("type") String type
			, @Param("price") int price);
	
	// 전달 받은 id는 delete 쿼리의 조건 부분에 쿼리를 완성 시키는데 들어가야 함
	// 그래서 id 변수 값이 xml에서 특정 키워드로 사용될 수 있도록 @Param 어노테이션을 매칭 시켜 줌
	public int deleteRealEstate(@Param("id") int id);
	
}
