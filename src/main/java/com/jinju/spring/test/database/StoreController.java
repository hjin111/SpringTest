package com.jinju.spring.test.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.database.domain.Store;
import com.jinju.spring.test.database.service.StoreService;

@Controller
public class StoreController {
	
	// 이것도 마찬가지로 다른 클래스 안에 있는 메소드이고 객체화 필요하다. 
	// 직접 만들어서 관리할 수 있지만 멤버 변수로 해당 클래스를 변수로 지정해주고 해당 변수에 객체를 채우는건 Autowired를 통해서 스프링한테 맡긴다.
	// 그럼 알아서 객체를 주입해 주기 떄문에 쓸 떄는 객체가 이미 만들어진거다라고 가정을 하고 마음껏 쓰면 됨
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/db/store/list")
	@ResponseBody // 해당하는 리스트 객체를 그대로 리턴해주면 httpmessageconvert라는 놈이 리턴 타입을 확인 해서 해당하는 리스트 객체를 jackson 라이브러리 라는 놈을 통해 문자열로 변환하고 그 문자열을 response에 담아서 content type은 application/json으로 잡아서 돌려준다.
	public List<Store> storeList() {
		// 이 상태에서 Request, Response인데 
		// Response는 결과를 다 만든 제일 마지막에 할 일이니깐 Request 부터 먼저 정리하기
		// Request 에서 신경 써야 할 것 첫번쨰 RequestMapping을 통해 url 매핑을 해준다.
		// 그 다음 이 페이지가 할 일에 대해서 정돈 해보기
		// 가게 정보 리스트를 json으로 response에 담는다.
		// 서블릿 같은 경우는 여기서 다 진행 해도 됨. ( 데이터 베이스 조회 하고 조회 된 결과를 잘 정리해서 json 문자열로 만들어가고 등등 )
		// 스프링 프레임 워크 기반 아래 각각의 역할을 명확하게 구분 해서 각 클래스별로 필요한 기능을 만들거고 각각의 역할이 아닌 부분은 거기에서 대응되서 사용할 클래스에 기능을 대신 수행시키는거다.
		// json을 response에 담는거는 controller의 역할 맞다.
		// 근데 가게 정보 리스트를 얻어 오는건 controller의 역할이 아니다.
		// 그래서 가게 정보 리스트를 얻어 오는 과정을 누구한테?? contorller 는 service 밖에 모른다. spring에 mvc 구조상 controller는 service 밖에 모른다
		// 그래서 store와 관련된 로직 부분에 기능을 수행해주는 service 클래스에 있는 메소드를 통해 이 controller 에서 가게 정보 리스트를 조회해 와야 한다. 
		// Store 관련 Service Class의 메소드를 통해 가게 정보 리스트를 얻어 온다.
		// 이게 스프링 MVC 기반에서 Controller 가 기능을 수행 하기 위해 해야 되는 과정이다. ( 무조건 Service 를 통해서 )
		// 무조건 Service 클래스에 있는 메소드를 통해서 필요한 기능을 수행 한다.
		// 그럼 Store와 관련되 Service 클래스를 찾아서 거기에 있는 가게 정보 리스트를 얻어 오는 메소드를 호출하기만 하면 됨
		List<Store> storeList = storeService.getStoreList();
		return storeList;
		
	}
}
