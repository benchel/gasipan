package gasipan.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonProperty;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.PostRequestDTO;
import gasipan.service.UserService;
import gasipan.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Slf4j
public class UserTestController {
	
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

	//@JsonProperty
	//private String OTP;
	
	/**
	@PostMapping("/post")
	public void post(@RequestBody Map<String, Object> requestData) {
		requestData.forEach((key, value) -> {
			System.out.println("key : " + key);
			System.out.println("value : " + value);
		});
	}
	*/
	
	/**
	@PutMapping("/put")
	public void put(@RequestBody PostRequestDTO postRequestDTO) {
		System.out.println(postRequestDTO.toString());
	}
	*/
	
	/**
	@PutMapping("/put/{userId}")
	public void put(@RequestBody PostRequestDTO postRequestDTO, @PathVariable String userId) {
		System.out.println(postRequestDTO.toString());
	}
	*/
	
	/* public void delete() {} */
	
}
