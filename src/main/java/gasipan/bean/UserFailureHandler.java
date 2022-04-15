package gasipan.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import gasipan.constant.GasipanConstructer;

public class UserFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 로그인 실패 시 로그인 화면으로 리다이렉트
		response.sendRedirect("/user/loginPage?error="+ GasipanConstructer.LOGIN_ERROR_CODE);
	}

}
