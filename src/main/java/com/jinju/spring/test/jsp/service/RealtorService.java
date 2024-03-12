package com.jinju.spring.test.jsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.jsp.domain.Realtor;
import com.jinju.spring.test.jsp.repository.RealtorRepository;

@Service
public class RealtorService { // Controller 에서 필요한 기능이 있어야 되는데 없어야 하니깐 만드는 거임

	@Autowired
	private RealtorRepository realtorRepository;
	
	// 리턴 타입은 저장한 이후에는 대다한 정보가 없음 그래서 실행된 행의 갯수를 전달해주면 좋을거 같아서 int 지정 
	public int addRealtorByObject(Realtor realtor){ 
		// realtor 이 객체 안에 들어 있는 정보를 기반으로 공인중개사 정보를 저장하는 거임 어디에다가? realtor 테이블에다가 저장
		// 테이블에다가 뭔가를 저장한다?? 데이터베이스 쿼리 수행 해야 한다는 얘기이고 Service 에서 해야 할 일이 아님 => Repository 에서 할 일
		int count = realtorRepository.insertRealtor(realtor);
		
		// 해당하는 insertRealtor 메소드는 전달된 realtor 객체 안에 들어있는 값을 기반으로 insert로 진행시키고
		// 그리고 나서 insert 된 행의 primary key 까지 얻어 와서 전달한 realtor 이 객체의 그 대응 되는 멤버 변수의 값까지 채워 준다
		// 그리고 나서 실행된 행의 갯수를 리턴 해줌.
		return count;	
	}
}
