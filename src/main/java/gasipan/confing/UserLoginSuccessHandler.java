package gasipan.confing;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import gasipan.dto.UsersDto;
import gasipan.service.UserService;
import gasipan.vo.UsersVo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * (의역) 
 * ExceptionTranslationFilter에 의해 세션에 저장했을지도 모르는 DefaultSavedRequest를 이용할 수 있는 authentication success strategy
 * 
 * 인증을 가로챘거나 인증이 필요한 경우, 요청 데이터는 인증 프로세스를 시작하기 전에 원래 목적지를 기록하며
 * 동일한 URL 발생으로 리다이렉트되었을 때 해당 요청이 재구성되도록 허용한다.
 */
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private UserService userServices; 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 현재 세션 가져오기
		HttpSession session = request.getSession();

		// 세션 검사
		if(session != null) {
			// 기존의 값들을 지운다.
			SessionManager.removeSeesionInfo(session);
		}
		
		String userId = ((UsersVo) authentication.getPrincipal()).getUserId();
		String authority = ((UsersVo) authentication.getPrincipal()).getAuthority();
		
		UsersVo userVo = userServices.loadUserByUsername(userId);
		
		try {
			SessionManager.setSeesionInfo(session, userVo);
		} catch (Exception e) {
			System.out.println("UserLoginSuccessHandler error : " + e.getMessage());
		}
		
		String targetUrl = "";
		
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		// 로그인 요청이 있었던 페이지를 기록했다면 해당 페이지의 url로 이동한다. 없는 경우 기본값으로 지정한 url로 이동
		if(savedRequest != null)
			targetUrl = savedRequest.getRedirectUrl();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
