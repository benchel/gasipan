package gasipan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasipan.bean.GasipanPasswordEncoder;
import gasipan.dto.AdminDTO;
import gasipan.repository.AdminDao;
import gasipan.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

	@Autowired
	private final AdminDao adminDao;
	
	@Transactional(readOnly = true)
	public AdminVO selectAdminById(String adminId) throws Exception {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setAdminId(adminId);
		
		return adminDao.selectAdminById(adminDTO);
	}
	
	
}
