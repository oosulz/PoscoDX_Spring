package guestbook.controller;

import java.util.Enumeration;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestbookRepository;
import guestbook.service.GuestbookService;
import guestbook.vo.GuestbookVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GuestbookController {

	private GuestbookService guestbookService;

	public GuestbookController(GuestbookService guestbookService) {
		this.guestbookService = guestbookService;
	}

	@RequestMapping("/")
	public String index( /* HttpServletRequest httpServletRequest */Model model) {
		/*
		 * ServletContext sc = httpServletRequest.getServletContext();
		 * Enumeration<String> e = sc.getAttributeNames(); while (e.hasMoreElements()) {
		 * String name = e.nextElement(); System.out.println(name); }
		 * 
		 * ApplicationContext ac1 = (ApplicationContext)sc.getAttribute(
		 * "org.springframework.web.context.WebApplicationContext.ROOT");
		 * ApplicationContext ac2 = (ApplicationContext)sc.getAttribute(
		 * "org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
		 * 
		 * GuestbookRepository repository = ac1.getBean(GuestbookRepository.class);
		 * 
		 * GuestbookController controller = ac2.getBean(GuestbookController.class);
		 * 
		 * System.out.println(repository); System.out.println(controller);
		 */

		model.addAttribute("list", guestbookService.getContentsList());
		return "index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteForm(@PathVariable("id") Long id) {
		return "delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, @RequestParam("password") String password) {
		System.out.println(id);
		guestbookService.deleteContents(id, password);
		return "redirect:/";
	}

	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookService.addContents(vo);
		return "redirect:/";
	}
}