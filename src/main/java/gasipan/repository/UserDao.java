package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;

import gasipan.dto.UsersDto;
import gasipan.vo.UsersVo;

@Mapper
public interface UserDao {
	public UsersVo selectUserById(UsersDto dto);
	public int insertUserInfor(UsersDto dto); 
}
