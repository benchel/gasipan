package gasipan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.constant.GasipanConstructer;
import gasipan.dto.AttachedFileDTO;
import gasipan.dto.BoardDTO;
import gasipan.repository.AttachedFileDAO;
import gasipan.repository.BoardDAO;
import gasipan.util.GasipanPagingUtil;
import gasipan.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreedomService {

	private final BoardDAO boardDAO;
	private final AttachedFileDAO attachedFileDAO;
	
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
		
		if(!boardDTO.getFileKeyList().isEmpty()) {
			AttachedFileDTO attachedFileDTO = new AttachedFileDTO();
			
			for(String fileKey : boardDTO.getFileKeyList()) {
				attachedFileDTO.setFileKey(fileKey);
				attachedFileDTO.setParentId(boardDTO.getBoardIdx());
				attachedFileDAO.updateAttachedFile(attachedFileDTO);
			}
		}
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
		
		long pageNo = boardDTO.getPageNo() == null ? 1 : boardDTO.getPageNo();
		long pageSize = boardDTO.getPageSize() == null ? 1 : boardDTO.getPageSize();
		long pageBlock = boardDTO.getPageBlock() == null ? 1 : boardDTO.getPageBlock();
		
		boardDTO.setPageNo(pageNo);
		boardDTO.setPageSize(pageSize);
		boardDTO.setPageBlock(pageBlock);
		boardDTO.setPageOffset(GasipanPagingUtil.getPageStartNo(pageNo, pageSize));
		
		List<BoardVO> list = boardDAO.selectBoardList(boardDTO);
		
		if(pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			boardDTO.setPageNo(pageNo);
			boardDTO.setPageOffset(GasipanPagingUtil.getPageStartNo(pageNo, pageSize));
			list = boardDAO.selectBoardList(boardDTO);
		}
		
		long totalCount = boardDAO.selectBoardCnt(boardDTO);
		long totalPageNo = GasipanPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHTML = GasipanPagingUtil.getPagingHTML(totalCount, pageNo, pageSize, pageBlock);
		
		rs.put("boardDTO", boardDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHTML", pagingHTML);
		
		return rs;
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> selectBoardByIdx(BoardDTO boardDTO) throws Exception {
		Map<String, Object> rs = new HashMap<>();
		
		AttachedFileDTO attachedFileDTO = new AttachedFileDTO();
		attachedFileDTO.setParentId(boardDTO.getBoardIdx());
		attachedFileDTO.setParentType(boardDTO.getBoardType());
		rs.put("fileList", attachedFileDAO.selectAttachedFileByParentId(attachedFileDTO));
		rs.put("board", boardDAO.selectBoardByIdx(boardDTO));

		return rs;
	}
	
	/**
	 * 자유게시판 목록
	 * @param boardDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> selectAdminBoardListWithPaging(BoardDTO boardDTO) throws Exception {
		Map<String, Object> rs = new HashMap<>();
		
		long pageNo = boardDTO.getPageNo() == null ? 1 : boardDTO.getPageNo();
		long pageSize = boardDTO.getPageSize() == null ? 1 : boardDTO.getPageSize();
		long pageBlock = boardDTO.getPageBlock() == null ? 1 : boardDTO.getPageBlock();
		
		boardDTO.setPageNo(pageNo);
		boardDTO.setPageSize(pageSize);
		boardDTO.setPageBlock(pageBlock);
		boardDTO.setPageOffset(GasipanPagingUtil.getPageStartNo(pageNo, pageSize));
		
		List<BoardVO> list = boardDAO.selectBoardList(boardDTO);
		
		if(pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			boardDTO.setPageNo(pageNo);
			boardDTO.setPageOffset(GasipanPagingUtil.getPageStartNo(pageNo, pageSize));
			list = boardDAO.selectBoardList(boardDTO);
		}
		
		long totalCount = boardDAO.selectBoardCnt(boardDTO);
		long totalPageNo = GasipanPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHTML = GasipanPagingUtil.getAdminPagingHTML(totalCount, pageNo, pageSize, pageBlock);
		
		rs.put("boardDTO", boardDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHTML", pagingHTML);
		
		return rs;
	}
	
}
