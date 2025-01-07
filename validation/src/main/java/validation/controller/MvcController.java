package validation.controller;


import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import validation.domain.User;

@Controller
public class MvcController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/ex01")
	public String ex01() {
		return "form/ex01";
	}

	@GetMapping("/result")
	public String result() {
		return "result";
	}

	@PostMapping("/ex01")
	public String ex01(@Validated User user, BindingResult result, Model model) {
	    System.out.println("Received User Object: " + user);
	    System.out.println("BindingResult has errors: " + result.hasErrors());
		
		if (result.hasErrors()) {
			// List<Object>

			Map<String, Object> map = result.getModel();
			Set<String> s = map.keySet();
			
			for (String key : s) {
				System.out.println(key);
				model.addAttribute(key, map.get(key));
			}
			
			return "form/ex01";
		}

		return "redirect:/result";
	}
}