package com.jinju.spring.test.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.mybatis.domain.RealEstate;
import com.jinju.spring.test.mybatis.repository.RealEstateRepository;

@Service // 사실 이 Service 어노테이션은 Autowired를 위한 스프링 빈에 등록하는 기능 이외에는 사실 큰 기능은 없음.
		 // Controller 같은 경우는 여기에 있는 메소드가 request를 처리하기 위해 만들어진 메소드라는거를 명시해 두는 역할로 기능이 있다.
		 // 근데 service 어노테이션은 사실 큰 기능은 없음
		 // 그래서 명시적으로 이 클래스가 service를 위한 클래스 라는 것과 나중에 Autowired 등을 통해서 스프링 빈에 등록하도록 해주는 기능 이정도만 있다 라고 생각하기
public class RealEstateService {
	
	@Autowired
	private RealEstateRepository realEstateRepository;

//	전달 받은 id를 기반으로 조회하는거니깐 파라미터로 id를 전달 받아야 함 이거는 그냥 순수한 자바 문법임
//	이 기능을 구현 하는 이유가 id를 기반으로 데이터를 조회하는 거니깐 id를 전달 받아야 되고 그러려면
//	메소드에 파라미터로 값을 전달 받도록 구성 해야 함
//	아까 request에서 뭘 전달 받는지 생각하지 말고 딱 이것만 독립적으로 생각 하기
//	Controller에서 필요한 메소드가 뭐다? id로 매몰 정보 얻어오는 것
//	그럼 id를 전달 받고 매몰 정보를 리턴하는 메소드를 만들어 주면 됨
	public RealEstate getRealEstate(int id) {
		// 이 id 값 가지고 일치하는 매몰 정보 얻어오면 됨 그걸 리턴해주기
		// 근데 그 정보는 데이터 베이스에 들어 있음
		// 그럼 결국 id 가지고 실제 데이터를 조회 하기 위해서는 데이터 베이스를 통해서 처리할 수 밖에 없다.
		// 근데 그게 service 역할이 아님
		// 그럼 service 에서는 repository를 통해서 쿼리를 수행하는 기능의 메소드를 호출 해서 원하는 기능을 수행 하고 그 결과를 가지고 다음 과정을 진행 해야 함
		RealEstate realEstate = realEstateRepository.selectRealEstate(id);
		return realEstate;
	}
	
	// 리턴 타입이 RealEstate는 딱 하나의 객체만 리턴되도록 하기 위한 형태
	// 그런데 이 메소드는 여러 개의 가능성이 있으니깐 List로 리턴 타입 정해주기 
	public List<RealEstate> getRealEstateListByRentPrice(int rentPrice){
		List<RealEstate> realEstateList = realEstateRepository.selectRealEstateListByRentPrice(rentPrice);
		return realEstateList;
	}
	
	public List<RealEstate> getRealEstateListByAreaAndPrice(int area, int price){
		// 두 개의 파라미터 정보를 통해 매몰 정보를 조회 하면 되는데 매몰 정보를 조회하는건 쿼리 기반으로 진행해야 함 
		// repository 메소드를 통해서 일단 데이터를 조회해 와야 여기 기능을 만들 수 있겠다.
		List<RealEstate> realEstateList = realEstateRepository.selectRealEstateListByAreaAndPrice(area, price);
		
		return realEstateList;
		
		// 특히 service 에서 사용되는 코드는 스프링에 사용법이 거의 들어 갈 일이 거의 없다
		// 순수한 자바, 순수하게 기능을 구현하기 위한 메소드라고 생각하면 됨
		// service 입장에서는 메소드 호출한게 다이다.
		// 서로 다른 메소드도 완전 독립 되어 있음 ( 클래스 안에 들어 있다고 해서 얘네들이 연관 관계를 가지지 않음, 메소드는 모두 독립적으로 되어 있고 필요한 곳에 그 메소드 호출해서 그 기능을 수행 할 뿐이다. )
		// 만들어져 있는 메소드는 서로 연관이 없다. 다만 공용으로 사용되는 멤버변수는 있을 수 있다.
		
	}
	
	public int addRealEstateByObject(RealEstate realEstate) {
		// 저장 한다 라는 개념이 데이터 베이스 테이블의 insert 하는 과정 이다. => service가 할 일이 아님
		// service는 테이블을 직접 쿼리를 통해서 조작하는 과정인 repository 메소드를 통해서 기능을 수행 해야 됨
		// service가 호출할 메소드는 realEstate 와 관련되어 있는 repository 인터페이스를 통해 저리하면 됨
		// insertRealEstateByObject는 mybatis 에 의해서 실행된 행의 갯수가 리턴이 됨
		int count = realEstateRepository.insertRealEstateByObject(realEstate);
		
		return count;
	}
	
	public int addRealEstate(
			int realtorId
			, String address
			, int area
			, String type
			, int price
			, int rentPrice) {
		
		// 파라미터 정보를 기반으로 한 행의 정보를 저장하면 되는데
		// 테이블 대상으로 기능 수행이 필요함 => service 기능이 아니고 테이블 관련된건 repository 를 통해서 기능 수행 하기
		int count = realEstateRepository.insertRealEstate(realtorId, address, area, type, price, rentPrice);
		return count; // 실행된 행의 갯수 return
	}
}
