package gasipan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
			"/css", "/img", "/js", "/admin/*"
	};
	
	@Bean
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns(USER_INTERCEPTOR_EXCLUDE_PATTERN_ARR);
	}
	
	
    // SpEL - Spring Expression Language
    @Value("${attached.load.uri}")
    private String resourceRealPath;
    
    @Value("${attached.load.root}")
    private String resourcePath;
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcePath)
				.addResourceLocations(resourceRealPath);
	}
	
}
