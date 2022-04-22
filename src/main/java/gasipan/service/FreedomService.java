package gasipan.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gasipan.repository.BoardDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/freedom/*")
public class FreedomService {

	private final BoardDAO boardDAO;
	
	
}
