package gasipan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/*")
public class JoinController {
	
	@GetMapping("/joinView")
	public String joinView() {
		return "/join";
	}
}
