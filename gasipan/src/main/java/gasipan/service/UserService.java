package gasipan.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasipan.dto.UsersDto;
import gasipan.repository.UserDao;
import gasipan.vo.UsersVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserDao userDao;
	
	/**
	 * 회원 조회
	 * 
	 * 유저이름(user를 식별하는 키)을 바탕으로 유저를 찾아낸다.
	 * Locates the user based on the username.
	 * 
	 * 실제 동작에서 검색은 구현 인스턴스가 구성되는 방식에 따라 대소문자를 구분하거나 대소문자를 구분하지 않을 수 있다.
	 *  In the actual implementation, the search may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured.
	 * 
	 *  이 경우, 반환되는 <code>UserDetails</code> 개체는 실제로 요청한 것과 다른 대소문자를 사용하는 사용자 이름을 가질 수 있다.
	 *  In this case, the <code>UserDetails</code> object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 * 
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 * GrantedAuthority
	 */	
	@Override
	public UsersVo loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersDto userDto = new UsersDto();
		userDto.setUserId(username);
		
		return userDao.selectUserById(userDto);
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
