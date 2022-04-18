package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;

import gasipan.dto.AdminDTO;
import gasipan.vo.AdminVO;

@Mapper
public interface AdminDao {
	public AdminVO selectAdminById(AdminDTO adminDTO);
}
