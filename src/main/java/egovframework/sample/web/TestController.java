package egovframework.sample.web;

import egovframework.sample.service.SampleService;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {

	@Resource(name = "sampleService")
	private SampleService sampleService;

	@RequestMapping(value = "/test.do") // 이용가능 자전거 수 카운트
	public String test() {

		BikeVO bike = new BikeVO(); // 테스트용 객체
		bike.setBikeReservePlaceId(11);
		bike.setBikeStatus(true);
		
		int result = sampleService.selectBikeCount(bike); // 이용가능 자전거수
		
		System.out.println("카운트 값확인" + result);
		
		return "test";
		
	}

	@RequestMapping(value = "/testReserve.do", method = RequestMethod.POST) // 예약 처리 테스트
	public String test2(String bikeReservePlaceId, String period) {

		BikeVO bike = new BikeVO(); // 테스트용 객체
		int bikeReservePlaceIdInt = Integer.parseInt(bikeReservePlaceId);
		System.out.println("int로 변환한 대여소번호 " + bikeReservePlaceIdInt);
		bike.setBikeReservePlaceId(bikeReservePlaceIdInt);
		bike.setBikeStatus(true);

		int bike_id = sampleService.selectBikeId(bike); // 예약될 자전거번호

		System.out.println("카운트 값확인" + bike_id);

		
		 Date currentDate = new Date(); //변환할시간객체
		  
		 ReservationVO rv = new ReservationVO(); //예약VO 객체
		 
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //00:00:00 형태로 변환
		 Time sqlTime = null; //선택한시간 Date타입으로 변환
		 
		 try {
		 
		 Date parseDate = sdf.parse(period);
		 
		 sqlTime = new Time(parseDate.getTime());
		  
		 } catch (ParseException e) {
		 
		 e.printStackTrace();
		 
		 }
		

//		  Date currentDate = new Date();
//		  
//		  ReservationVO rv = new ReservationVO();
//		  
//		  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); Timestamp sqlTime =
//		  null; //선택한시간 Date타입으로 변환
//		  
//		  try {
//		  
//			  Date parseDate = sdf.parse(period);//선택되온 시간 (테스트용)
//			  
//			  sqlTime = new Timestamp(parseDate.getTime());
//		  
//		  } catch (ParseException e) {
//		 
//			  e.printStackTrace();
//		 
//		  }

		System.out.println("시간변환" + sqlTime);

		rv.setUserId("tkj");// 유저id (jsp에서 받아오기) 합칠때 session에서 가져오기
		rv.setBikeId(bike_id); // 자전거id
		rv.setStartTime(new Date(currentDate.getTime())); // 현재시간
		rv.setPeriod(sqlTime); // 이용시간 (jsp에서 받아오기)

		int result = sampleService.insertReservation(rv);

		if (result >= 1) { // 예약성공시

			System.out.println("예약정보입력 성공");
			int result2 = sampleService.changeStatus(bike_id);

			if (result2 >= 1) {

				System.out.println("예약상태변경성공");

			}

		}

		/*
		 * rv.setUserId(userId); rv.setPeriod(period); rv.set
		 */
		return "forward:reserveTest.do";

	}

	@RequestMapping(value = "/reserveTest.do", method = { RequestMethod.GET, RequestMethod.POST }) // 대여소 위치확인 페이지이동
	public String reservPage(Model model, ModelAndView mv) throws Exception {
		
		List<BikeReservePlaceVO> placelist = sampleService.selectBikePlace(); //자전거 전체 대여소 리스트
		BikeReservePlaceVO br = new BikeReservePlaceVO();
		for(BikeReservePlaceVO list:placelist) {
			
			BikeVO bike = new BikeVO();
			bike.setBikeReservePlaceId(list.getReservePlaceId());
			bike.setBikeStatus(true);
			br.setCount(sampleService.selectBikeCount(bike)); 
			
			list.setCount(sampleService.selectBikeCount(bike));
			System.out.println("확인"+list.getCount());
		}
		
		model.addAttribute("rentList", placelist); // 대여소 리스트 모델 등록
		
	    // 리스트를 JSON 문자열로 변환
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonPlacelist = mapper.writeValueAsString(placelist);
	    
	    model.addAttribute("jsonPlacelist", jsonPlacelist); // 대여소 리스트 모델 등록
		// mv.addObject("rentList", daojdbc.selectRent());

		return "reservTest";

	}

	@RequestMapping(value = "/searchTest.do", method = RequestMethod.POST) // 리스트 검색
	@ResponseBody
	public ResponseEntity searchRent(@RequestBody BikeReservePlaceVO testRent, Model model) throws SQLException {
		System.out.println("컨트롤러확인1");
		ResponseEntity result = null;

		try {

			List<BikeReservePlaceVO> list = sampleService.selectSearchBikePlace(testRent);
			result = ResponseEntity.ok().body(list);

		} catch (Exception e) {

			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return result;

	}

	@RequestMapping(value = "/reserveGoTest.do", method = RequestMethod.POST) // 예약정보 컨트롤러로 넘기기 테스트
	@ResponseBody
	public String reserveGo(String bikeReservePlaceId, String period) throws SQLException {
		System.out.println("컨트롤러확인1" + bikeReservePlaceId);

		// BikeReservePlaceVO brpv, ReservationVO rv

		return null;

	}

}