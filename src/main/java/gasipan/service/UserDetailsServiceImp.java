package gasipan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gasipan.dto.UserDTO;
import gasipan.repository.UserDAO;
import gasipan.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private final UserDAO userDao;
	
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
	public UserVO loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(username);
		
		return userDao.selectUserById(userDto);
	}

}
