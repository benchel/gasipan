package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;
import gasipan.dto.AttachedFileDTO;

@Mapper
public interface AttachedFileDAO {
	public void insertAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
	public void deleteAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
	public void updateAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception;
}
