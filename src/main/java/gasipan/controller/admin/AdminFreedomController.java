package gasipan.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/admin/freedom/*")
@NoArgsConstructor
public class AdminFreedomController {

	@GetMapping("/list")
	public String listHTML() throws Exception {
		return "admin/freedom/list";
	}
}
