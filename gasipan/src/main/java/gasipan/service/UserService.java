package gasipan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasipan.dto.UsersDto;
import gasipan.repository.UserDao;
import gasipan.vo.UsersVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDao userDao;
	
	@Transactional(readOnly = true)
	public UsersVo selectUserById(UsersDto dto) {
		return userDao.selectUserById(dto);
	}
}
