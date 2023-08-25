package egovframework.sample.web;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.sample.service.SampleService;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;

@Controller
public class TestController {
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value = "/test.do") // 이용가능 자전거 수 카운트
	public String test() {
		
		BikeVO bike = new BikeVO(); //테스트용 객체
		bike.setBikeReservePlaceId(11);
		bike.setBikeStatus(true);
		
		int result = sampleService.selectBikeCount(bike); //이용가능 자전거수
		
		System.out.println("카운트 값확인"+result);
		
		return "test";
		
	}

	@RequestMapping(value = "/testReserve.do") // 예약 처리 테스트
	public String test2() {
		
		BikeVO bike = new BikeVO();//자전거 id 가져올 테스트용 객체
		bike.setBikeReservePlaceId(11);
		bike.setBikeStatus(true);
		
		int bike_id = sampleService.selectBikeId(bike); //받아올 자전거 id
		Date currentDate = new Date();
		
		ReservationVO rv = new ReservationVO();
		
		String choiceTime = "03:00:00"; //선택되온 시간 (테스트용)
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Time sqlTime = null; //선택한시간 Date타입으로 변환
		
		try {
		
			Date parseDate = sdf.parse(choiceTime);
			
			sqlTime = new Time(parseDate.getTime());
			
		} catch (ParseException e) {
		
			e.printStackTrace();
		
		}
		
		rv.setUserId("tkj");//유저id (jsp에서 받아오기)
		rv.setBikeId(bike_id); //자전거id
		rv.setStartTime(new Date(currentDate.getTime())); //현재시간
		rv.setPeriod(sqlTime); //이용시간 (jsp에서 받아오기)
		
		int result = sampleService.insertReservation(rv);
		
		if(result>=1) { //예약성공시
			
			System.out.println("예약정보입력 성공");
			int result2 = sampleService.changeStatus(bike_id);
			
			if(result2>=1) {
				
				System.out.println("예약상태변경성공");
				
			}
			
		}
		
		/*
		 * rv.setUserId(userId); rv.setPeriod(period); rv.set
		 */
		return "test";
		
	}
	
}