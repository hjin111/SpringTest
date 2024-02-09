package com.jinju.spring.test.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // @Controller + @ResponseBody
@RequestMapping("/lifecycle/test02")
public class Test02Controller {

	@RequestMapping("/1")
	public List<Map<String, Object>> movieList() {
		
		// 3개의 Map이 모두 똑같은 key를 가지고 있고 그에 대응되는 값만 영화 정보에 따라 다르게 저장되어 있습니다.
		// 이걸 각각 하나하나 다 리턴할 수는 없다 하나의 메소드에서 딱 하나의 객체만 리턴할 수 있다.
		// 3가지를 하나를 묶을 방법을 생각해보자 => 똑같은 형태의 데이터를 저장하는건 list만한게 없다.
		
		List<Map<String, Object>> movieList = new ArrayList<>();

		Map<String, Object> movieMap = new HashMap<>();
		movieMap.put("title", "기생충"); // 해당하는 map에 정해진 값을 key - value 순서로 채워주기 
		movieMap.put("director", "봉준호");
		movieMap.put("time", 131);
		movieMap.put("rate", 15);
		movieList.add(movieMap); // 리스트에 만들어진 map을 추가해주면 자연스럽게 해당 map이 쭉 list에 추가됨

		Map<String, Object> movieMap2 = new HashMap<>();

		movieMap2.put("title", "인생은 아름다워");
		movieMap2.put("director", "로베르토 베니니");
		movieMap2.put("time", 116);
		movieMap2.put("rate", 0);
		movieList.add(movieMap2);

		Map<String, Object> movieMap3 = new HashMap<>();

		movieMap3.put("title", "인셉션");
		movieMap3.put("director", "크리스토퍼 논란");
		movieMap3.put("time", 147);
		movieMap3.put("rate", 12);
		movieList.add(movieMap3);
		
		return movieList;

	}
	
	@RequestMapping("/2")
	public List<Post> postList(){
		
		List<Post> postList = new ArrayList<>();
		Post post = new Post("안녕하세요. 가입인사 드립니다.", "hagulu", "안녕하세요 가입했어요. 잘 부탁 드립니다.");
		
		postList.add(post);
		
		// 변수는 단순히 잠시 잠깐 해당 객체를 가르키면서 그 객체를 다루기 위해서 쓰이는 겁니다.
		// 그렇기 때문에 위에 있는 post 라는 변수 다 썼으니 다시 재 사용하면 됨
		// 새로운 객체를 만들고 새로운 객체를 가르키는, 새로운 객체를 다루기 위한 변수로 재사용 한다
		post = new Post("헐 대박", "bada", "오늘 목요일이었어, 금요일인줄");
		postList.add(post);
		
		post = new Post("오늘 데이트한 이야기 해드릴게요", "dulumary", "....");
		postList.add(post);
		
		return postList;
	}
	
	// ResponseEntity 라고 하는 객체를 통해서 우리가 원하는 response 내용과 우리가 원하는 status 코드를 응답으로 전달할 수 있다.
	@RequestMapping("/3")
	public ResponseEntity<Post> postError(){
		Post post = new Post("안녕하세요. 가입인사 드립니다.", "hagulu", "안녕하세요 가입했어요. 잘 부탁 드립니다.");
		
		// response body에 post 객체를 넣고 response status 코드에 내가 원하는 status 코드를 넣기 위해 ResponsEntity 객체를 생성해서 써야 된다. 
		ResponseEntity<Post> entity = new ResponseEntity<>(post, HttpStatus.INTERNAL_SERVER_ERROR);
		// entity라는 객체를 리턴해 주면 이 객체 안에 들어 있는 response body에 담을 post라는 객체와 인자로 전달해준 HttpStatus.INTERNAL_SERVER_ERROR 이 status 코드를 통해 response를 완성해준다.
		
		return entity;
		
	}
	
}
