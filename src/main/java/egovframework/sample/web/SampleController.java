package egovframework.sample.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.sample.service.SampleService;

@Controller
public class SampleController {
	
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	@RequestMapping(value = "/test.do") // 테스트용
	public String test() {
		
		
		
		return "test";
		
	}


}