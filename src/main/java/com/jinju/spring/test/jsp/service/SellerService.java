package com.jinju.spring.test.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.jsp.domain.Seller;
import com.jinju.spring.test.jsp.repository.SellerRepository;

@Service
public class SellerService {
	
	// service 에서 insertSeller 이 메소드를 사용하려면 repository 객체가 필요함
	// 해당 하는 객체는 직접 만들어서 관리 하기 보다는 @Autowired를 통해서 멤버 변수 형태로 정의해주면 이 변수에 해당 객체를 사용할 때
	// 알아서 객체가 주입이 되니깐 우리는 원래 만들어져 있는 것처럼 마음껏 객체를 사용하면 됨
	@Autowired
	private SellerRepository sellerRepository;
	
	// 여기서 nickname, temperature, profileImage 전달 받고 해당 하는 데이터를 저장해주는 기능을 Service 에서 메소드 형식으로 만든다.
	public int addSeller(String nickname, double temperature, String profileImage) {
		
		int count = sellerRepository.insertSeller(nickname, temperature, profileImage);
		
		return count;
	}
	
	public Seller getLastSeller() {
		Seller seller = sellerRepository.selectLastSeller();
		
		return seller;
	}
	
	// id 를 기반으로 조회해서 해당하는 판매자 정보를 리턴해주는 메소드
	public Seller getSeller(int id) { // id 가 일치하는건 딱 한 행이고 한 행의 데이터를 저장 하기 위해서는 Entity 클래스 만들어놨으니깐 하나의 객체만 리턴하면 되기 때문에 리턴 타입 Seller로 해주기 
		
		// id 기반으로 seller 정보를 조회하는건 select 쿼리를 통해서 조회해 와야 함. => service가 repository 메소드를 통해서 해당 기능을 수행 해야 함
		Seller seller = sellerRepository.selectSeller(id); // Entity 클래스로 만들어진 객체를 하나 mybatis가 직접 객체 생성해서 값까지 채워서 리턴해준다
		
		return seller;

	}
}
