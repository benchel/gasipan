package gasipan.confing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 권한이 없는 사용자의 접근을 처리한다.
 */
public class AccessDeniedHandlerImp implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 필요한 권한이 user인데 해당 권한이 없을 때 /user/loginPage로 이동
		// 필요한 권한이 admin인데 해당 권한이 없을 때 /admin/loginPage로 이동
	}

}
