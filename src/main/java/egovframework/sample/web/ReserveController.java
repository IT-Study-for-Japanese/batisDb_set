package egovframework.sample.web;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.service.SampleService;
import egovframework.sample.service.SampleVO;
import egovframework.sample.service.TestVo;
import egovframework.sample.vo.BikeReservePlaceVO;

@Controller 
public class ReserveController {
	
	@Resource(name="sampleService")
	private SampleService sampleService; //서비스
//
//	@RequestMapping("/test.do")
//	public String test() {
//		return "hello";
//	}
//	
//	@RequestMapping(value="/test2.do")	
//	@ResponseBody
//	public String test2() {
//		
//		return "test2.."; 
//	}
	
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

	 @RequestMapping(value="/reserveHome.do", method = {RequestMethod.GET, RequestMethod.POST}) //대여소 위치확인 페이지이동
		public String reservPage(Model model, ModelAndView mv) throws Exception {
			
			model.addAttribute("rentList",sampleService.selectBikePlace()); //대여소 리스트 모델 등록
			//mv.addObject("rentList", daojdbc.selectRent());
			
			return "reservHome";
			
		}
	 
	@RequestMapping(value="/search.do",method = RequestMethod.POST) //리스트 검색 
	@ResponseBody
	public ResponseEntity searchRent(@RequestBody BikeReservePlaceVO testRent ,Model model) throws SQLException {
		System.out.println("컨트롤러확인1");
		ResponseEntity result = null;
			
		try {
			
		    List<BikeReservePlaceVO> list = sampleService.selectSearchBikePlace(testRent.getReservePlaceName());
		    result = ResponseEntity.ok().body(list);

		} catch (Exception e) {
				
		    result = ResponseEntity
		            .status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body(e.getMessage());
		}
			
		return result; 
			
		}
	}
