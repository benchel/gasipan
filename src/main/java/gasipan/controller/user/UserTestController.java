package gasipan.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserTestController {

	@GetMapping("/")
	public String view() {
		return "user/index";
	}

	@GetMapping("/loginPage")
	public String loginView() {
		return "userLogin";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "user/myPage";
	}
	
}
