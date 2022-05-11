package gasipan.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import lombok.extern.slf4j.Slf4j;


/**
 * *[] 생략 가능 
 * 요청(Request) -> Filter -> DispatcherServlet -> *[HandlerInterceptor] -> Controller 
 * 인터셉터는 컨트롤러의 핸들러가 실행되기 전에 추가적인 작업이 수행되어야하는 경우 사용한다.
 * 
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
	
}
