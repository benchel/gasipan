package gasipan.confing;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * (의역) 
 * ExceptionTranslationFilter에 의해 세션에 저장했을지도 모르는 DefaultSavedRequest를 이용할 수 있는 authentication success strategy
 * 
 * 인증을 가로챘거나 인증이 필요한 경우, 요청 데이터는 인증 프로세스를 시작하기 전에 원래 목적지를 기록하며
 * 동일한 URL 발생으로 리다이렉트되었을 때 해당 요청이 재구성되도록 허용한다.
 */
@NoArgsConstructor
@Slf4j
public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	public UserLoginSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		// 세션 검사 
		// 널이면 다시 세팅
		// 관리자단의 값들이 세션에 속성으로 들어가있으면 지우고 다시 세팅
		
		String targetUrl = getDefaultTargetUrl();
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
