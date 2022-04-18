package gasipan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gasipan.dto.AdminDTO;
import gasipan.repository.AdminDao;
import gasipan.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminDetailsServiceImp implements UserDetailsService {

	@Autowired
	private final AdminDao adminDao;
	
	@Override
	public AdminVO loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setAdminId(username);
		
		return adminDao.selectAdminById(adminDTO);
	}

}
