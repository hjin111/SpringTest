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
	
	// controller 메소드의 역할은 request 와 response 처리
	// 일단 요청하는 부분 부터 시작할거니깐 request 와 관련된 부분 부터 정리 하기
	@RequestMapping("/insert/1")
	@ResponseBody
	public String createRealEstateByObject() {
		
		// 주어진 항목을 객체 형태로 insert 하기
		// realtorId : 3 address : 푸르지용 리버 303동 1104호 area : 89 type : 매매 price : 100000
		// 위에 값들을 저장 해서 하나로 묶을 수 있는 클래스가 있으면 그 클래스 기반으로 객체 생성 하면 됨
		// 우리가 기존에 RealEstate 를 조회 해서 한 행의 정보를 저장 하기 위한 클래스를 Entity 클래스 형태로 만들어 놓았으니 이거 활용 하기
		
		// 해당 하는 클래스로 객체 생성 해서 위에 주어진 값들을 setter 를 통해서 값을 채우기
		RealEstate realEstate = new RealEstate(); // 객체 생성 했으면 멤버 변수를 저장 할수 있는 공간 까지 확보 됨 그러니 그 멤버 변수 안에 주어진 값들을 채워 넣기
		realEstate.setRealtorId(3);
		realEstate.setAddress("푸르지용 리버 303동 1104호");
		realEstate.setArea(89);
		realEstate.setType("매매");
		realEstate.setPrice(100000);
		
		// realEstate 이 객체 안에 들어 있는 정보들을 그에 대응 되는 컬럼에 저장 할 수 있도록할거다.
		// 데이터를 저장하는 기능은 controller가 하는 일이 아님
		// controller는 service 에 있는 메소드를 통해서 기능을 수행시켜서 필요한 기능을 처리할거임
		// controller에 realEstate 객체를 전달 받아서 저장 하기 위한 기능을 메소드를 통해서 수행 시켜 볼거임
		// 매몰과 관련된 로직 부분을 처리 하는 realEstate service 클래스가 이미 있고 여기 안에 realEstate 객체를 통해서 저장 하는 기능이 없으니 만들자
	
		int count = realEstateService.addRealEstateByObject(realEstate);
		
		return "입력 성공 : " + count;
	}
	
	@RequestMapping("/insert/2") // url 매핑
	@ResponseBody
	public String createRealEstate(@RequestParam("realtorId") int realtorId) {
		// 이 파라미터 변수에 저장될 값이 뭐다?? 요청하는 쪽에서 전달한 realtorId가 될거 같음
		// 그러면 realtorId 이 값에 저장될 파라미터의 이름을 매칭 시켜 줘야 그 파라미터로 저장된 값이 realtorId 이 변수에 저장 된다.
		
		// realtorId 데이터랑 주어진 데이터를 하나하나 따로 전달해서 한 행의 정보를 저장하고자 한다
//		address : 썅떼빌리버 오피스텔 814호
//		area : 45
//		type : 월세
//		price : 100000
//		rentPrice : 120
		int count = realEstateService.addRealEstate(realtorId, "썅떼빌리버 오피스텔 814호", 45, "월세", 100000, 120);
		
		return "입력 성공 : " + count;
	}
	
	@RequestMapping("/update")
	@ResponseBody // 특정한 형태의 문자열을 responsebody로 채우기 위해서 리턴 타입 string 오고 responsebody 어노테이션을 붙여서 원하는 형태 문자열을 리턴해주기
	public String updateRealEstate() {
		// id가 23인 매몰 정보에 type을 전세, 보증금 70000으로 변경
		// 값을 수정하는 기능을 수행해야 하는데 controller 역할이 아님
		// 이 기능을 수행 하기 위한 service 메소드 호출해 줘야 함
		int count = realEstateService.updateRealEstate(23, "전세", 70000);
		
		return "수정 성공 : " + count;
	}
	
	@RequestMapping("/delete")
	@ResponseBody // 문자열을 responsebody에 담기 
	public String deleteRealEstate(@RequestParam("id") int id) { // id 이 변수 값에 저장 될 값이 requestParameter이기 때문에 특정한 이름에 매칭 해서 id 변수에 값이 저장이 되야 된다.  
		// 전달 받은 id 와 일치 하는 데이터를 삭제 해야 하니깐 삭제 기능이 수행되야 함 
		// 삭제 기능이 수행 되기 위해서는 service 메소드를 통해서 삭제 해야 함
		// service에 id를 전달 받아서 그에 대응 되는 행을 삭제해주는 기능을 호출
		int count = realEstateService.deleteRealEstate(id);
		return "삭제 성공 : " + count;
		
	}
	
}
