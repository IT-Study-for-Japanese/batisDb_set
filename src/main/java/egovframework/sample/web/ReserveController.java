package egovframework.sample.web;

import egovframework.sample.service.SampleService;
import egovframework.sample.service.SampleVO;
import egovframework.sample.service.TestVo;
import egovframework.sample.vo.BikeReservePlaceVO;
import egovframework.sample.vo.BikeVO;

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

import javax.annotation.Resource;
import java.io.File;
import java.sql.SQLException;
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

	@RequestMapping(value="/insert.do",method = RequestMethod.GET)
	public String insertSampleView(SampleVO vo) throws Exception{
		return "insertSample";
	}
	
	@RequestMapping(value="/insertSample.do",method = RequestMethod.POST)
	public String insertSample(SampleVO vo) throws Exception {
		sampleService.insertSample(vo);
		
		return "forward:selectSample.do";
	}
	
	@RequestMapping("/selectSample.do")
	@ResponseBody
	public String selectSample(SampleVO vo,Model model) throws Exception {
		return "저장 후  selectSample";
	}
	
	 @RequestMapping(value = "/insertTest.do",method = RequestMethod.POST)
	    public String write(@ModelAttribute("testVo") TestVo testVo) throws Exception {
		 System.out.println("파일업로드");
		 System.out.println(testVo.getUploadFile());
	        String fileName = null;
	        MultipartFile uploadFile = testVo.getUploadFile();
	        if (!uploadFile.isEmpty()) {
	            String originalFileName = uploadFile.getOriginalFilename();
	            String ext = FilenameUtils.getExtension(originalFileName); // 확장자 구하기
	            UUID uuid = UUID.randomUUID(); // UUID 구하기
	            fileName = uuid + "." + ext;
	            uploadFile.transferTo(new File("C:\\upload\\" + fileName));
	        }
	        testVo.setFileName(fileName);
	 
	        System.out.println(testVo.getFileName());
	 
	        //testService.insertTest(testVo);
	 
	        return "";
	    }

	@RequestMapping(value = "/reserveHome.do", method = {RequestMethod.GET, RequestMethod.POST}) //대여소 위치확인 페이지이동
	public String reservPage(Model model, ModelAndView mv) throws Exception {

		
		List<BikeReservePlaceVO> placelist = sampleService.selectBikePlace(); //자전거 전체 대여소 리스트
		
		
		boolean hasData = placelist.stream().filter(i -> Objects.nonNull(i)).findAny().isPresent();
		
		if(hasData) {
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
		    result = ResponseEntity.ok().body(list);

		} catch (Exception e) {
				
		    result = ResponseEntity
		            .status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body(e.getMessage());
		}
			
		return result;
	}
}
