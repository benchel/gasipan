package gasipan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import gasipan.constant.GasipanConstructer;
import lombok.extern.slf4j.Slf4j;


/**
 * *[] 생략 가능 
 * 요청(Request) -> Filter -> DispatcherServlet -> *[HandlerInterceptor] -> Controller 
 * 인터셉터는 컨트롤러의 핸들러가 실행되기 전에 추가적인 작업이 수행되어야하는 경우 사용한다.
 * 
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 인증객체 가져오기
		HttpSession session = request.getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 인증객체가 null 값이 아니면 
		if(authentication != null) {
			// 인증객체의 타입 확인
			if(authentication.getPrincipal() instanceof User) {
				User user = User.class.cast(authentication.getPrincipal());
				
				// 스프링 시큐리티에는 로그인 정보가 있는데 HTTP 세션에는 로그인 관련 정보가 없는 경우 로그아웃 처리
				if(session.getAttribute(GasipanConstructer.SESSION_USER_ID) == null) {
					log.info("session timeout : {}", user.getUsername());
					response.sendRedirect("/user/logout");
				}
				
			}
		} // if(authentication != null) {}
	}
	
}
