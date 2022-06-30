package gasipan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import gasipan.dto.AttachedFileDTO;
import gasipan.vo.AttachedFileVO;

@Mapper
public interface AttachedFileDAO {
	public void insertAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
	public void deleteAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
	public void updateAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
	public List<AttachedFileVO> selectAttachedFileByParentId(AttachedFileDTO attachedFileDTO) throws Exception;
}
