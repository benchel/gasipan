package gasipan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasipan.bean.GasipanPasswordEncoder;
import gasipan.dto.UserDTO;
import gasipan.repository.UserDAO;
import gasipan.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final GasipanPasswordEncoder sitePasswordEncoder;
	private final UserDAO userDao;
	
	/**
	 * 회원가입
	 * @param dto
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor={Exception.class})
	public void join(UserDTO dto) throws Exception {
		if(null == dto.getUserId() || dto.getUserId().equals("")) {
			throw new Exception("Required parameter missing.");
		}
		
		// 비밀번호 암호화
		dto.setUserPwd(sitePasswordEncoder.encode(dto.getUserPwd()));
		
		// DB에 저장
		userDao.insertUserInfor(dto);
	}
	
	/**
	 * 회원 찾기
	 * @param dto
	 * @return
	 */
	@Transactional(readOnly = true)
	public UserVO selectUserById(String userId) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		
		return userDao.selectUserById(userDTO);
	}
	

}
