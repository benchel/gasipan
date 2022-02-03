package gasipan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.bean.GasipanConstructer;
import gasipan.service.UserService;
import gasipan.vo.UsersVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class HomeController {
	
	private final HttpSession session;
	private final UserService userService;
	
	@GetMapping("/")
	public String index(ModelMap modelMap) {
		
		String SESSION_USER_ID = (String) session.getAttribute(GasipanConstructer.SESSION_USER_ID);
		UsersVo user = userService.loadUserByUsername(SESSION_USER_ID);
		
		try {
			modelMap.addAttribute("ssUser", user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "index";
	}
}
