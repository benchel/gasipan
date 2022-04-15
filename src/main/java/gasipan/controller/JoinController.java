package gasipan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gasipan.dto.UserDTO;
import gasipan.service.UserService;
import gasipan.vo.GenericVo;
import gasipan.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/*")
@Slf4j
public class JoinController {
	
	private final UserService userService;
	
	/**
	 * 회원가입 view
	 * @return
	 */
	@GetMapping("/joinView")
	public String joinView() {
		return "join";
	}
	
	/**
	 * 회원가입
	 * @param dto
	 */
	@PostMapping("/join")
	@ResponseBody
	public GenericVo<UserVO> join(UserDTO dto) {
		
		GenericVo<UserVO> userVo = new GenericVo<>();
		
		try {
			userService.join(dto);
			// 가입 성공
			userVo.setMessage("1");
		} catch (Exception e) {
			// 가입 실패
			userVo.setMessage("0");
			log.info(e.getMessage());
		}
		
		return userVo;
	}
	
}
