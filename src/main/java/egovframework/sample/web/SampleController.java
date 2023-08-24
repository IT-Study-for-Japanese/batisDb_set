package egovframework.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@ResponseBody
	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	public String test() {
		return "World";
	}
}