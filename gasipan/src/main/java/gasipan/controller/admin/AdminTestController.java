package gasipan.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminTestController {

	
	@GetMapping("/loginPage")
	public String loginView() {
		return "/adminLogin";
	}
	
	@GetMapping("/index")
	public String view() {
		return "/admin/index";
	}
}
