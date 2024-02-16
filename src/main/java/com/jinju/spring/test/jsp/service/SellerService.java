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
}
