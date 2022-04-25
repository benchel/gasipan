package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;

import gasipan.dto.BoardDTO;

@Mapper
public interface BoardDAO {
	public void insertBoard(BoardDTO boardDTO) throws Exception;
}
