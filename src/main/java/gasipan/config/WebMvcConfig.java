package gasipan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gasipan.interceptor.UserInterceptor;

/**
 * WebMvcConfigurer
 * (오역 주의)
 * Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc.
 * @EnableWebMvc를 이용하여 구동되는 스프링 MVC에 대한 자바 기반 환경으로 커스텀된 콜백 메서드를 정의한다.
 * @EnableWebMvc-annotated configuration classes may implement this interface to be called back and given a chance to customize the default configuration.
 * @EnableWebMvc 주석이 달린 설정 클래스는 이 인터페이스(WebMvcConfigurer)를 구현하여 다시 호출하거나 기본 구성을 커스텀할 수 있다.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	public static final String[] USER_INTERCEPTOR_EXCLUDE_PATTERN_ARR = {
			"/css", "/image", "/js", "/admin/*"
	};
	
	@Bean
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
}
