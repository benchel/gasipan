package gasipan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String index(ModelMap modelMap) {
		try {
			modelMap.addAttribute("appleColor", "red");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "index";
	}
}
