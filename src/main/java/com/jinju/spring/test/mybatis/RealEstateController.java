package com.jinju.spring.test.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.mybatis.domain.RealEstate;
import com.jinju.spring.test.mybatis.service.RealEstateService;

@Controller // Controller 어노테이션이 붙어 있어야 이 클래스를 요청과 응답을 처리하기 위한 클래스로 인식을 해서 url 매핑에 관련된 것들이 이 클래스 기반으로 정리가 됨 
			// 그래서 controller 어노테이션이 없으면 이 클래스가 request 를 처리하기 위한 메소드가 있다라는 판단을 안하기 때문에 여기 아무리 url 매핑을 해놓아도 여기 있는 메소드로 페이지를 만들 수 없다.
@RequestMapping("/mybatis/real-esate")
public class RealEstateController {
	
	@Autowired // 변수에다가 자동으로 객체가 주입이 되도록 @Autowired 어노테이션 붙여준다
	private RealEstateService realEstateService;
	
	// 우리가 만드는 이 controller는 request와 response와 관련된 처리를 진행한다 라고 했음
	// 메소드 만들어 갈 때 괄호 안에 들어갈게 전달 받을 파라미터가 있다면 여기서 정의해주는거다.
	@RequestMapping("/select/1")
	@ResponseBody // realEstate 이 객체를 JSON 형태로 바로 변환시켜서 멤버 변수 이름을 KEY 그에 대응 되는 값을 VALUE로 하는 형태로 JSON 문자열을 만들면 되겠다 라고 판단을 해서 controller 에서 responsebody 어노테이션을 메소드 위에 얹어주면 리턴 될 객체를 httpmessageconvert 라는 놈이 알아서 판단해서 response를 채워줌 
				  // realEstate 이 객체를 가지고 response에 JSON 문자열 채워야 함 그 과정을 손쉽게 처리해주는 어노테이션
	public RealEstate realEstate(@RequestParam("id") int id) { // 어떤 파라미터 이름으로 전달 될 값을 저장 할 변수인지를 어노테이션을 통해서 @RequestParam() 이 어노테이션을 통해서 파라미터 이름을 지정해주면 됨
		// 기존에 우리가 request의 getParameter 메소드의 인자로 파라미터 이름을 지정해서 그 이름으로 값을 전달 받듯이
		// 여기서는 RequestParam 이라는 어노테이션을 통해서 파라미터 이름을 지정해 주면 됨!!!
		// id 라는 이름으로 전달된 값이 (int) id 라는 변수에 저장이 된다.
		
		// 파라미터로 전달된 id와 일치하는 매몰 정보를 json 문자열로 response에 담는다.
		// id와 일치하는 매몰 정보를 찾아 오는 과정은 controller의 역할이 아님 
		// controller의 역할이 아니면?? controller는 무조건 기능과 관련된 부분들은 Service를 통해서 수행한다. ( service 클래스에 특정 메소드를 호출하면 됨 => service 클래스 만들기 )
		RealEstate realEstate = realEstateService.getRealEstate(id);
		
		return realEstate;
		
	}
	
	// rent 라는 이름으로 전달 된 파라미터 값이 rentPrice 이 변수에 저장 되서 자바 메소드에 일반 파라미터 처럼 사용할 수 있도록 해주는거다.
	@RequestMapping("/select/2")
	@ResponseBody
	public List<RealEstate> realEstateListByRentPrice(@RequestParam("rent") int rentPrice) {
		// rentPrice 에 저장된 값 보다 더 낮은 매몰 정보 얻어와서 json 문자열로 response에 담기
		// request를 처리 하고 response를 처리 하는 과정이 아닌건 다 service에 있는 메소드를 통해서 기능 수행을 해야 함
		// 그런데 매몰 정보와 관련된 기능을 수행하는 realEstateService 클래스는 이미 만들어 놓았음!! 굳이 새로 만들 필요 없음
		// 그래서 우리는 메소드를 호출해서 기능을 호출하는 거니깐 필요한 기능을 메소드로 만들어서 추가하면 됨 
		List<RealEstate> realEstateList = realEstateService.getRealEstateListByRentPrice(rentPrice);
		return realEstateList;
	}
	
	
	@RequestMapping("/select/3")
	@ResponseBody
	public List<RealEstate> realEstateListByAreaAndPrice(
			@RequestParam("area") int area
			, @RequestParam("price") int price){
			// 조회 하는 과정은 controller는 아무것도 모른다.
			// 그냥 이거 줄 테니깐 조회즘 해줘 라고 service 메소드를 통해서 기능 수행을 해야 함
			List<RealEstate> realEstateList = realEstateService.getRealEstateListByAreaAndPrice(area, price);
			return realEstateList;
	}
	

}
