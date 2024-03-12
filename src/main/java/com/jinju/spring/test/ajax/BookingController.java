package com.jinju.spring.test.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.ajax.domain.Booking;
import com.jinju.spring.test.ajax.service.BookingService;

@RequestMapping("/ajax/booking")
@Controller
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/main")
	public String bookingMain() {
		return "ajax/booking/main";
	}
	
	@GetMapping("/list")
	public String bookingList(Model model) {
		
		List<Booking> bookingList = bookingService.getBookingList();
		
		model.addAttribute("bookingList", bookingList);
	
		return "ajax/booking/list";
		
	}

	@GetMapping("/input")
	public String bookingInput() {
		return "ajax/booking/input";
	}
	
	@GetMapping("/create")
	@ResponseBody
	public Map<String, String> createBooking(
			@RequestParam("name") String name
			// 2023년09월08일
			, @DateTimeFormat(pattern="yyyy년MM월dd일") @RequestParam("date") Date date
			, @RequestParam("day") int day
			, @RequestParam("headcount") int headcount
			, @RequestParam("phoneNumber") String phoneNumber) {
		
		int count = bookingService.addBooking(name, date, day, headcount, phoneNumber);
		
		// 성공 : {"result":"success"}
		// 실패 : {"result":"fail"}
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
	}
	
	// 삭제 API
	@GetMapping("/delete")
	@ResponseBody
	public Map<String, String> deleteBooking(@RequestParam("id") int id) { // 삭제 대상의 id
		
		int count = bookingService.deleteBooking(id);
		
		// API니깐 response 에 데이터가 담겨야 된다. 삭제 이후에 성공/실패 여부 만들어서 담기
		// 성공 : {"result":"success"}
		// 실패 : {"result":"fail"}
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) { // id를 기반으로 삭제 했기 때문에 1이어야 한다. ( 한 행만 삭제가 되어야 함 )
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	// 이름과 전화번호를 전달 받고, 일치하는 예약 정보를 돌려주는 API ( 돌려준다는 얘기는 response 에 담아서 전달해준다는 얘기 )
	@GetMapping("/search")
	@ResponseBody
	public Map<String, Object> searchBooking(
			@RequestParam("name") String name
			, @RequestParam("phoneNumber") String phoneNumber){
		
		Booking booking = bookingService.getBooking(name, phoneNumber); // booking 객체로 예약 정보 얻어오기 
		
		// 일치하는 예약 정보를 돌려준다는 얘기는 일치하는 예약 정보를 response에 담는다는 얘기인데
		// booking 이 객체 안에 들어가 있는 데이터를 json 문자열로 만들어서 채워놓으면 response 에 담긴다.
		// @ResponseBody 어노테이션 붙여주고 리턴 타입으로 booking 클래스를 잡아 준 다음에 이 값을 그대로 리턴해주면 
		// 조회된 booking 객체를 json 문자열 형태로 만들어서 response 에 담아줄 겁니다.
		// 이게 API 이다.
		
		// API를 만드는 과정에서 조회 결과가 있는지 없는지를 명확히 구분 해서 response를 구성
		// 조회 성공 실패 여부를 response에 추가
		// 조회 성공시 : {"result":"success", "booking":booking} 성공시에는 success 뿐만 아니라 실제 조회된 결과까지 채워서 전달 해줘야 함
		//								   "booking":{"name":"혜리","date":"2024-02-26","day":2,...}
		// 조회 실패시 : {"result":"fail"}
		
		Map<String, Object> resultMap = new HashMap<>();
		if(booking != null) { // 성공 조회 되면 booking 이라는 객체가 만들어져 있고 조회가 안되어 있으면 null 일 것이다.
			// 조회 성공
			resultMap.put("result", "success");
			resultMap.put("booking", booking);
		} else {
			// 조회 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
	
}
