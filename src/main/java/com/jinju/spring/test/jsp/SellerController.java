package com.jinju.spring.test.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.jsp.domain.Seller;
import com.jinju.spring.test.jsp.service.SellerService;

@Controller
@RequestMapping("/jsp/seller")
public class SellerController {
	
	@Autowired
	private SellerService sellerService; // service 객체가 필요하니 멤버 변수 형태로 객체 변수 만들어서 @Autowired 어노테이션 붙여주면 
										 //해당 하는 멤버 변수에 객체가 알아서 만들어서 주입 될거임
	
	// 전달 받을 값들 파라미터로 전달
	// nicname 이라는 파라미터에 저장 될 값이 요청에 포함 되어 있는 Request 파라미터의 값이 되어야 하니깐 파라미터에는 이름이 부여 되서
	// 그 이름으로 전달된 값이 들어 있음 그래서 파라미터에 이름을 부여 하고 이름으로 전달된 값을 해당 하는 파라미터 변수에 저장 할 수 있도록 구성 한다.
	// 그걸 위해서는 @RequestParam 어노테이션이 필요함.
	// @RequestParam 어노테이션에 있는 값이 우리가 서블릿에서 파라미터 이름을 지정하던 getParameter() 메소드의 인자에 들어가는 값과 매칭이 되는 부분이다.
	// nickname, temperature, profileImage 얘네들은 그저 그걸로 얻어진 값들을 저장을 위한 변수일 뿐이다. 
	
	// profileImage는 주소 형식이기 때문에 주소 형식이 max로 차게 되면 파라미터에 포함 되는 주소가 그 범위를 넘어 갈 수 있다.
	// 그래서 얘는 get 메소드가 아니라 post 메소드로 처리를 할 것이다.
	// 파라미터 중에 너무 길어질 수 있는 데이터가 포함 되어 있어서 Post 형식의 페이지로 만들도록 할 것이다. 그걸 위해서 PostMapping을 통해 url 매핑을 할 것이다.
	@PostMapping("/create")
	@ResponseBody // 리턴값인 문자열이 response에 body 부분에 담기
	public String createSeller( // @RequestParam 안에 있는 이 파라미터 이름들로 값들이 전달 되서 nickname, temperature, profileImage 이 변수들에 저장 될 거임
			@RequestParam("nickname") String nickname
			, @RequestParam("temperature") double temperature
			, @RequestParam("profileImage") String profileImage) { 
		
		// nickname, temperature, profileImage 이 세 변수에 저장된 값들을 실제 저장 하는 과정을 수행해야 하는데 Controller가 하는 일이 아님
		// 그럼 Controller 가 원하는 기능이 뭐다?? nickname, temperature, profileImage를 전달 받고 이 값을 저장 해주는 기능이 필요 하다.
		// Service를 통해서 이 기능을 메소드로 사용 하기
		int count = sellerService.addSeller(nickname, temperature, profileImage);
		
		return "수행결과 : " + count;
	}
	
	@GetMapping("/input") // 파라미터 따로 없으니깐 바로 GetMapping 해주기
	public String inputSeller() {
		// 해당 하는 jsp 경로, response에 담을 jsp 경로를 return 해주면 됨
		return "jsp/sellerInput";
	}
	
	@GetMapping("/info")
	public String sellerInfo(Model model) {
		
		Seller seller = sellerService.getLastSeller(); // 리턴하는 jsp 안에 가장 최근에 등록된 판매자 정보를 채우기 위해서 해당 jsp를 리턴하는 메소드에서 필요한 정보를 얻어옴 
		
		// seller 라는 객체 정보를 jsp에서 쓸 수 있도록 해주는 세팅 해주기 
		model.addAttribute("seller", seller); // model 이라고 하는 객체에 조회해서 얻어온 정보를 seller 라는 이름으로 객체로 저장했습니다.
											  // 왜? model 에다가 저장하면 jsp 에서 그 값을 저장한 key 이름으로 꺼내쓸 수 있기 때문이다.
		
		// jsp에 데이터를 채우고 싶으면 jsp 경로를 리턴하는 Controller 메소드에서 그 데이터 정보를 얻어 온다.
		return "jsp/sellerInfo";
	}
	
}
