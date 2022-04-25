package gasipan.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.constant.GasipanConstructer;
import gasipan.service.UserService;
import gasipan.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

	private final HttpSession session;
	private final UserService userService;
	
	/**
	 * 마이페이지
	 * @param modelMap
	 * @return
	 */
	@GetMapping({""})
	public String myPage(ModelMap modelMap) {
		
		try {
			String SESSION_USER_ID = (String) session.getAttribute(GasipanConstructer.SESSION_USER_ID);
			UserVO user = userService.selectUserById(SESSION_USER_ID);
			
			modelMap.addAttribute("ssUser", user);
		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
		}
		
		return "user/myPage";
	}
}
