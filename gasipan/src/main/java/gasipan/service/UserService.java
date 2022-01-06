package gasipan.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
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
	
	/**
	 * 회원 조회
	 * @param dto
	 * @return
	 */
	@Transactional(readOnly = true)
	public UsersVo selectUserById(UsersDto dto) {
		return userDao.selectUserById(dto);
	}
	
	/**
	 * 회원가입
	 * @param dto
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor={Exception.class})
	public void join(UsersDto dto) throws Exception {
		if(null == dto.getUserId() || dto.getUserId().equals("")) {
			throw new Exception("Required parameter missing.");
		}
		
		// 비밀번호 암호화
		dto.setUserPwd(BCrypt.hashpw(dto.getUserPwd(), BCrypt.gensalt()));
		
		// DB에 저장
		userDao.insertUserInfor(dto);
	}
	
}
