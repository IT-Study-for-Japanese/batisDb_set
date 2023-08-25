package egovframework.sample.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.sample.service.SampleService;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.BikeReservePlaceVO;

@Controller
public class TestController {
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value = "/test.do") // 테스트용
	public String test() {
		
		BikeVO bike = new BikeVO();
		bike.setBike_reserve_place_id(11);
		bike.setBike_status(true);
		
		int result = sampleService.selectBikeCount(bike); //이용가능 자전거수
		
		System.out.println("카운트 값확인"+result);
		
		return "test";
		
	}

}