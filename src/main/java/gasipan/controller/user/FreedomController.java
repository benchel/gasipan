package gasipan.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.service.FreedomService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freedom/*")
public class FreedomController {

	private final FreedomService freedomService;
	
	@GetMapping("/list")
	public String list() {
		return "user/freedom/list";
	}
	
	@GetMapping("/regist")
	public String regist() {
		return "user/freedom/regist";
	}
	
}
