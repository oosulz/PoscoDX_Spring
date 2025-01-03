package locale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/locale")
public class LocaleController {
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@GetMapping
	public String index(HttpServletRequest request) {
		String locale =  localeResolver.resolveLocale(request).getLanguage();
		System.out.println("resolver-locale: " + locale);
		return "index";
	}
}
