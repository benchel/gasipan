package gasipan.confing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 권한이 없는 사용자의 접근을 처리한다.
 */
@Slf4j
public class AdminAccessDeniedHandlerImp implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		// 요청이 있었던 url 
		String url = request.getRequestURL().substring(0);
		
		log.info("request URL in AccessDeniedHandler : " + url);
		
		// 리다이렉트
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		// 권한이 없는 사용자의 접근이 있을때 사용자가 가야할 경로 지정
		String targetUrl = "/admin/loginPage";
		// 해당 목적지를 바탕으로 요청과 응답 다시 설정 
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
