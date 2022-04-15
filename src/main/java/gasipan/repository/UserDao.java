package gasipan.repository;

import org.apache.ibatis.annotations.Mapper;

import gasipan.dto.UserDTO;
import gasipan.vo.UserVO;

@Mapper
public interface UserDao {
	public UserVO selectUserById(UserDTO dto);
	public int insertUserInfor(UserDTO dto); 
}
