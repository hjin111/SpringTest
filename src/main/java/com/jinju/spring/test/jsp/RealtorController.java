package com.jinju.spring.test.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinju.spring.test.jsp.domain.Realtor;
import com.jinju.spring.test.jsp.service.RealtorService;

@Controller
@RequestMapping("/jsp/realtor")
public class RealtorController {
	
	@Autowired
	private RealtorService realtorService; // service 객체 생성은 직접 하지 않을거고 멤버 변수로 변수 만들어준다음에 @Autowired 어노테이션을 통해서 스프링을 통해 객체 관리 하도록 구성 시켜 준다.

	// realtor 라는 테이블을 대상으로 한 행을 저장 하기 위한 insert 페이지이다.
	// 여기서 필요한 값들, insert 필요한 값들을 파라미터로 하나하나 전달 받은 거고
	// 이 전달 받은 값을 기반으로 insert를 하면 되는데 우리가 목표로 하는 것은 
	// insert 한 이후에 primary key 를 저장 해서 그것까지 활용하기 위한 과정이 필요함!!
	// office, phoneNumber, address, grade 이 값들을 전달해서 insert 하는거 뿐만 아니라 이 값을 전달한 다음에
	// primary key를 저장할 수 있는 공간까지 준비가 필요하다
	// office, phoneNumber, address, grade 이것들을 저장할수도 있고 primary key도 저장 할 수 있는 형태의 데이터
	// 아무래도 Entity 클래스의 객체가 좋을거 같음 이렇게 판단을 해서 realtor 테이블에 대한 Entity 클래스를 만들었다.
	
	@GetMapping("/create")
	public String createReator(
			@RequestParam("office") String office
			, @RequestParam("phoneNumber") String phoneNumber
			, @RequestParam("address") String address
			, @RequestParam("grade") String grade
			, Model model){
		
		// 파라미터 전달 받은 값을 객체 형태로 구성을 해야 하니깐 우리가 직접 객체를 생성해야 됨
		// 전달 받은 값들을 새로운 realtor 객체를 만들어서 realtor 객체 안에 있는 setter 메소드를 통해 각각의 멤버 변수에 필요한 값들을 저장한다.
		// primary key인 id는 아직 insert 되기 전이니깐 채울 방법이 없음
		Realtor realtor = new Realtor();
		
		realtor.setOffice(office);
		realtor.setPhoneNumber(phoneNumber);
		realtor.setAddress(address);
		realtor.setGrade(grade);
		
		int count = realtorService.addRealtorByObject(realtor); // 전달한 객체는 여기서 직접 만들어서 값을 세팅한 객체
		// 해당 하는 객체 정보를 기반으로 데이터를 저장 해주고 저장된 primary key 까지 해당 하는 객체 안에 저장시켜 준다. 그 이후에 실행된 행의 갯수 리턴해 줌
		model.addAttribute("realtor", realtor); // 방금 insert 한 정보를 갖고 있는 realtor 이 객체 정보를 return 할 jsp 페이지에서 쓸 수 있게 해주면 됨
		
		return "jsp/realtorInfo";
		
	}
	
	// jsp 를 통해 html 결과물을 response에 담기 위해서는 jsp 경로를 리턴해야 한다.
	@GetMapping("/input")
	public String inputRealtor() {
		return "jsp/realtorInput";
	}
	
}
