package egovframework.sample.web;

import egovframework.sample.service.SampleService;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;
import egovframework.sample.vo.ReservationVO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.io.File;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@EnableWebMvc
public class ReserveController {
	
	@Resource(name="sampleService")
	private SampleService sampleService; //서비스

	@Value("#{config['naver.map.key']}")
	private String mapKey;

	/*
	 * @RequestMapping(value = "/insertTest.do",method = RequestMethod.POST) public
	 * String write(@ModelAttribute("testVo") TestVo testVo) throws Exception {
	 * System.out.println("파일업로드"); System.out.println(testVo.getUploadFile());
	 * String fileName = null; MultipartFile uploadFile = testVo.getUploadFile(); if
	 * (!uploadFile.isEmpty()) { String originalFileName =
	 * uploadFile.getOriginalFilename(); String ext =
	 * FilenameUtils.getExtension(originalFileName); // 확장자 구하기 UUID uuid =
	 * UUID.randomUUID(); // UUID 구하기 fileName = uuid + "." + ext;
	 * uploadFile.transferTo(new File("C:\\upload\\" + fileName)); }
	 * testVo.setFileName(fileName);
	 * 
	 * System.out.println(testVo.getFileName());
	 * 
	 * //testService.insertTest(testVo);
	 * 
	 * return ""; }
	 */

	@RequestMapping(value = "/reserveHome.do", method = {RequestMethod.GET, RequestMethod.POST}) //대여소 위치확인 페이지이동
	public String reservPage(Model model, ModelAndView mv) throws Exception {

		
		List<BikeReservePlaceVO> placeList = sampleService.selectBikePlace(); //자전거 전체 대여소 리스트
		
		boolean hasData = placeList.stream().filter(i -> Objects.nonNull(i)).findAny().isPresent();
		
		if(hasData) {
			BikeReservePlaceVO br = new BikeReservePlaceVO(); //대여소 객체생성
			for(BikeReservePlaceVO list:placeList) {
				
				BikeVO bike = new BikeVO();
				bike.setBikeReservePlaceId(list.getReservePlaceId());
				bike.setBikeStatus(true);
				br.setCount(sampleService.selectBikeCount(bike)); 
				
				list.setCount(sampleService.selectBikeCount(bike));
				System.out.println("확인"+list.getCount());
			}
			
			model.addAttribute("rentList", placeList); // 대여소 리스트 모델 등록
			ObjectMapper mapper = new ObjectMapper();
			String jsonPlacelist = mapper.writeValueAsString(placeList);
			
			model.addAttribute("jsonPlacelist", jsonPlacelist); // 지도표시 리스트 모델 등록
		
		}
		
		model.addAttribute("mapKey", mapKey);
		return "reserveHome";

	}

	@RequestMapping(value="/search.do",method = RequestMethod.POST) //리스트 검색
	@ResponseBody
	public ResponseEntity searchRent(@RequestBody BikeReservePlaceVO search ,Model model) throws SQLException {
		
		ResponseEntity result = null;
			
		try {
			List<BikeReservePlaceVO> list = sampleService.selectSearchBikePlace(search);
			
			for (BikeReservePlaceVO list2 : list) {

				BikeVO bike = new BikeVO();
				bike.setBikeReservePlaceId(list2.getReservePlaceId());
				bike.setBikeStatus(true);
				

				list2.setCount(sampleService.selectBikeCount(bike));
				System.out.println("검색확인" + list2.getCount());
			}
			
			
			result = ResponseEntity.ok().body(list);

		} catch (Exception e) {
				
		    result = ResponseEntity
		            .status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body(e.getMessage());
		}
			
		return result;
	}
	
	
	@RequestMapping(value="/reserveAjaxTest.do",method = RequestMethod.POST) // Ajax 예약처리 확인
	@ResponseBody
	public ResponseEntity reserveAjaxTest(@RequestBody ReservationVO reserve ,Model model) throws SQLException {
		
		System.out.println("컨트롤러 확인" + reserve.getTextPeriod());
		System.out.println("컨트롤러 확인" + reserve.getBikeReservePlaceId());
		
		BikeVO bike = new BikeVO(); // 테스트용 객체
		
		
		bike.setBikeReservePlaceId(reserve.getBikeReservePlaceId()); //대여소번호 대입
		bike.setBikeStatus(true); //가능여부 대입
		System.out.println("컨트롤러 확인" + reserve.getBikeReservePlaceId());
			
		int bike_id = sampleService.selectBikeId(bike); // 예약될 자전거번호
			
		System.out.println("자전거번호확인" + bike_id);
			
		Date currentDate = new Date(); // 변환할시간객체
		
		ReservationVO rv = new ReservationVO(); // 예약VO 객체
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // 00:00:00 형태로 변환
		Time sqlTime = null; // 선택한시간 Date타입으로 변환
		
		try {
			
			Date parseDate = sdf.parse(reserve.getTextPeriod());

			sqlTime = new Time(parseDate.getTime());
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
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
		
		return null;
		
	}
}
