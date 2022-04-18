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
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Slf4j
public class UserTestController {

	private final HttpSession session;
	private final UserService userService;

	/**
	 * main 화면
	 * @return
	 */
	@GetMapping("/")
	public String view() {
		return "user/index";
	}

	/**
	 * 로그인 페이지
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/loginPage")
	public String loginView() throws Exception {
		
		return "user/userLogin";
	}
	
	/**
	 * 마이페이지
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/myPage")
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

	/**
	@PostMapping("/post")
	public void post(@RequestBody Map<String, Object> requestData) {
		requestData.forEach((key, value) -> {
			System.out.println("key : " + key);
			System.out.println("value : " + value);
		});
	}
	*/
}
