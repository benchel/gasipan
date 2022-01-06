package gasipan.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class UserTestController {

	@GetMapping("/index")
	public String view() {
		return "/user/index";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "/user/myPage";
	}

}
