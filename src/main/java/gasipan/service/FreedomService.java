package gasipan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.BoardDTO;
import gasipan.repository.BoardDAO;
import gasipan.vo.BoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/freedom/*")
public class FreedomService {

	private final BoardDAO boardDAO;
	
	
	/**
	 * 자유게시판 등록
	 * @param boardDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Map<String, Object> registFreedom(BoardDTO boardDTO) throws Exception {
		Map<String, Object> rs = new HashMap<>();
		
		boardDAO.insertBoard(boardDTO);
		rs.put("message", GasipanConstructer.RESULT_SUCCESS);
		
		return rs;
	}
	
	/**
	 * 자유게시판 목록
	 * @param boardDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectBoardListWithPaging(BoardDTO boardDTO) throws Exception {
		Map<String, Object> rs = new HashMap<>();
		List<BoardVO> list = boardDAO.selectBoardList(boardDTO);
		
		return rs;
	}
	
}
