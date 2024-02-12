package com.jinju.spring.test.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.database.domain.Store;
import com.jinju.spring.test.database.repository.StoreRepository;

@Service
public class StoreService {
	
//	스프링에서 controller, service, repository와 관련된 객체는 직접 만드는 것 보다 객체 생성과 관리를 스프링한테 맡긴다.
//	왜? 웹 서비스 자체는 워낙 많은 접속이 들어오기 때문에 객체 관련과 이런 요소 조금이라도 너무 과하게 부여가 되면 서버에 부하가 걸림
//	리소스가 많이 사용된다라고 함 그래서 리소스 사용을 최소화 하기 위한 방식으로 객체가 관리가 되도록 스프링 프레임 워크 안에서 내부적으로 관리하도록 하는 거다. 그 방법이 객체로 사용할 변수를 멤버 변수를 만들고 임포트 하고 이 멤버 변수의 값을 직접 객체 생성해서 저장하는게 아니라
//	Autowired라는 어노테이션을 통해 해당하는 객체 값이 주입 된다라고 표현을 한다. 객체가 만들어서 주입이 되도록 정의를 해주면 된다.
//	그러면 객체가 생성되어 있으면 있는걸로 쓰고 없으면 생성해서 쓰도록 해준다.
//	autowired가 써 있으면 객체가 있다라고 가정을 하고 맘껏 사용하면 됨 
	@Autowired
	private StoreRepository storeRepository;
	
	
	// Service 어노테이션을 붙이는 순간 로직을 관리하는, Cotroller 에서 호출하는 메소드를 구성하기 위한 클래스로 정의가 됨
	// 가게 정보 리스트를 돌려주는 기능
	// controller에서 service 클래스를 통해 이 기능을 수행하기로 했었음 없으니깐 만드는 거임 ( 클래스에서 기능은 당연히 메소드로 구현 해야 함 )
	// service는 사용법이랄게 하나도 없음 그냥 무조건 자바 문법 기반이다.이거는 자바 문법 기반으로 필요한 메소드를 만들기만 하면 된다.
	
	public List<Store> getStoreList() {
		// 이 안에서 가게 정보 리스트 얻어 와야 돌려줄 수 있음 
		// 가게 정보 리스트는 데이터베이스 테이블 안에 들어가 있다.
		// service 클래스는 데이터베이스를 직접적으로 동작 시킬 수 없다 왜? 그렇게 역할을 나누어서 만들기로 했으니깐 
		// store 테이블의 가게정보 조회 결과를 Repository 메소드를 통해 얻어 온다.
		List<Store> storeList = storeRepository.selectStoreList();
		return storeList;
	}
	

}
