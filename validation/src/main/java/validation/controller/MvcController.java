package validation.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
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

	@GetMapping("/ex02")
	public String ex02() {
		return "form/ex02";
	}

	@GetMapping("/ex03")
	public String ex03(@ModelAttribute User user) {
		return "form/ex03";
	}

	@GetMapping("/result")
	public String result() {
		return "result";
	}

	@PostMapping("/ex01")
	public String ex01(@ModelAttribute @Valid User user, BindingResult result, Model model) {
		System.out.println("Received User Object: " + user);
		System.out.println("BindingResult has errors: " + result.hasErrors());

		if (result.hasErrors()) {
			// List<ObjectError> errors = result.getAllErrors();
			// for(ObjectError error : errors) {
			// System.out.println(error);
			// }

			Map<String, Object> map = result.getModel();
			/*
			 * Set<String> s = map.keySet();
			 * 
			 * for (String key : s) { System.out.println(key); model.addAttribute(key,
			 * map.get(key)); }
			 */
			model.addAllAttributes(map);
			return "form/ex01";
		}

		return "redirect:/result";
	}

	@PostMapping("/ex02")
	public String ex02(@ModelAttribute @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "form/ex02";
		}

		return "redirect:/result";
	}

	@PostMapping("/ex03")
	public String ex03(@ModelAttribute @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "form/ex03";
		}

		return "redirect:/result";
	}
}