package gasipan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.BoardDTO;
import gasipan.repository.BoardDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/freedom/*")
public class FreedomService {

	private final BoardDAO boardDAO;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Map<String, Object> registFreedom(BoardDTO boardDTO) throws Exception {
		Map<String, Object> rs = new HashMap<>();
		
		boardDAO.insertBoard(boardDTO);
		rs.put("message", GasipanConstructer.RESULT_SUCCESS);
		
		return rs;
	}
	
	
}
