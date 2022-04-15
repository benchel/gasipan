package gasipan.controller.admin;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.UserDTO;
import gasipan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminTestController {

	private final HttpSession session;
	private final UserService userService;
	
	@GetMapping("/")
	public String view(ModelMap modelMap) {
		
		try {
			String SESSION_USER_ID = (String) session.getAttribute(GasipanConstructer.SESSION_USER_ID);
			//UserVO user = userService.loadUserByUsername(SESSION_USER_ID);
			
			//modelMap.addAttribute("admin", user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "admin/index";
	}
	
	@GetMapping("/loginPage")
	public String loginView(@Param("error") String error, ModelMap modelMap) {
		modelMap.addAttribute("error", error);
		
		return "admin/adminLogin";
	}

}
