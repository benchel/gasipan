package gasipan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gasipan.dto.BoardDTO;
import gasipan.vo.BoardVO;

@Mapper
public interface BoardDAO {
	public void insertBoard(BoardDTO boardDTO) throws Exception;
	public long selectBoardCnt(BoardDTO boardDTO) throws Exception;
	public List<BoardVO> selectBoardList(BoardDTO boardDTO) throws Exception;
}
