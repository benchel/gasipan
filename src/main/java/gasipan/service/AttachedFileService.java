package gasipan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasipan.dto.AttachedFileDTO;
import gasipan.repository.AttachedFileDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachedFileService {

	private final AttachedFileDAO attachedFileDAO;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void registAttachedFile(AttachedFileDTO attachedFileDTO) throws Exception {
		attachedFileDAO.insertAttachedFile(attachedFileDTO);
	}
}
