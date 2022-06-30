package gasipan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


/**
 * *[] 생략 가능 
 * 요청(Request) -> Filter -> DispatcherServlet -> *[HandlerInterceptor] -> Controller 
 * 인터셉터는 컨트롤러의 핸들러가 실행되기 전에 추가적인 작업이 수행되어야하는 경우 사용한다.
 * 
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
	
	/**
	 * Controller 실행 직전에 동작
	 * return 값이 true인 경우 정상적으로 진행, false인 경우 컨트롤러로 진입하지 않고 실행 종료
	 * @param handler : HandlerMapping이 찾은 Controller를 의미함
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//log.info("preHandle() 실행");
		return true;
	}
	
	/**
	 * 뷰(view)가 렌더링되기 전에 수행된다.
	 * 뷰에 출력될 데이터 조작 가능
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String requestURI = request.getRequestURI();
		
		try {
			if(modelAndView != null) {
				
				if(requestURI.indexOf("/freedom/") >= 0) {
					modelAndView.addObject("parentType", "freedom");
				} else {
					modelAndView.addObject("parentType", "");
				}
				
			} // if(modelAndView != null) {} end
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		
	}
	
}
