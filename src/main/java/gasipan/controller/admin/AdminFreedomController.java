package gasipan.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gasipan.controller.user.FreedomController;
import gasipan.dto.BoardDTO;
import gasipan.service.FreedomService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/freedom/*")
@RequiredArgsConstructor
@Slf4j
public class AdminFreedomController {

	private final FreedomService freedomService;
	
	@GetMapping("/list")
	public String listHTML(@ModelAttribute("boardDTO") BoardDTO boardDTO) throws Exception {
		return "admin/freedom/list";
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
		return new ResponseEntity<>(freedomService.selectAdminBoardListWithPaging(boardDTO), HttpStatus.OK);
	}
	
}
