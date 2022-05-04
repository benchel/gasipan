package gasipan.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gasipan.dto.BoardDTO;
import gasipan.service.FreedomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freedom/*")
@Slf4j
public class FreedomController {

	private final FreedomService freedomService;
	
	/**
	 * 자유게시판 목록화면
	 * @return
	 */
	@GetMapping("/list")
	public String list(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		return "user/freedom/list";
	}
	
	/**
	 * 자유게시판 목록 검색
	 * @param boardDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/list-paging")
	@ResponseBody
	public ResponseEntity<?> getFreedomList(@RequestBody BoardDTO boardDTO) throws Exception {
		return new ResponseEntity<>(freedomService.selectBoardListWithPaging(boardDTO), HttpStatus.OK);
	}
	
	/**
	 * 자유게시판 등록화면
	 * @return
	 */
	@GetMapping("/regist")
	public String registHTML() {
		return "user/freedom/regist";
	}
	
	/**
	 * 자유게시판 등록
	 * @param boardDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/write")
	@ResponseBody
	public ResponseEntity<?> regist(@RequestBody BoardDTO boardDTO) throws Exception {
		return new ResponseEntity<>(freedomService.registFreedom(boardDTO), HttpStatus.OK);
	}
	
}
