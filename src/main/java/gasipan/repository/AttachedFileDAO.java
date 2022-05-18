package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;
import gasipan.dto.AttachedFileDTO;

@Mapper
public interface AttachedFileDAO {
	public void insertAttachedFile(AttachedFileDTO attachedFileDTO);
	public void deleteAttachedFile(AttachedFileDTO attachedFileDTO);
}
