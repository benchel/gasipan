package gasipan.confing;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * AuthenticationSuccessHandler
 * 
 * 성공적인 유저 인증을 처리하는데 사용되는 전략 
 * 	
 * 구현은 원하는 모든 작업을 수행할 수 있지만 일반적인 동작으로는 다음 목적지로 네비게이션을 조정하는 것이 있다. (리다이렉션 또는 포워드 사용)
 * 
 *  예를 들면, 사용자가 로그인 폼을 제출하여 로그인된 후에 애플리케이션은 결정해야한다.
 * 나중에 리다이렉트되는 위치 ({@link AbstractAuthenticationProcessingFilter} 또는 subclasses를 본다.)
 * 필요한 경우 다른 로직도 포함한다.
 */
public class AdminLoginSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * 사용자가 인증에 성공했을 때 호출한다.
	 * @param 성공적인 인증을 일으킨 요청 
	 * @param 그에 대한 응답
	 * @param 체인의 다른 필터들을 진행하는데 사용할 수 있는 {@link FilterChain} 
	 * @param 인증 프로세스 동안 생성된 <tt>Authentication</tt> 오브젝트
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// 현재 세션 가져오기
		// 세션 검사 
		// 널이면 다시 세팅
		// 사용자단의 값들이 세션에 속성으로 들어가있으면 지우고 다시 세팅
		
		// 리다이렉트
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		// 로그인 이후 가야할 목적지 지정
		String targetUrl = "/admin/";
		// 해당 목적지를 바탕으로 요청과 응답 다시 설정 
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	
}
