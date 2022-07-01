package gasipan.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/photo-album/*")
@Slf4j
public class PhotoAlbumController {

	@GetMapping("/list")
	public String listHTML(ModelMap modelMap) throws Exception {
		return "/user/photoAlbum/list";
	}
}
