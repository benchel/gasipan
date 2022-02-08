package gasipan.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@RequestMapping("/user/*")
@Slf4j
public class UserTestController {

	private final HttpSession session;
	private final UserService userService;
	
	@GetMapping("/")
	public String view() {
		return "user/index";
	}

	@GetMapping("/loginPage")
	public String loginView() {
		return "user/userLogin";
	}
	
	@GetMapping("/myPage")
	public String myPage(ModelMap modelMap) {
		
		try {
			String SESSION_USER_ID = (String) session.getAttribute(GasipanConstructer.SESSION_USER_ID);
			UsersVo user = userService.loadUserByUsername(SESSION_USER_ID);
			
			modelMap.addAttribute("ssUser", user);
		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		
		return "user/myPage";
	}
	
}
