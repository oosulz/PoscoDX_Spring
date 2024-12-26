package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @RequestMapping 클래스 단독 매핑
 * Spring MVC 4.x 지원
 * 
 */

@Controller
public class BoardController {
	
	@ResponseBody
	@RequestMapping("board/write")
	public String write() {
		return "BoardController:write()";
	}
	
	@ResponseBody
	@RequestMapping("board/view/{no}")
	public String view(@PathVariable("no") Long num) {
		return "BoardController:view("+ num +")";
	}
	
	
}
