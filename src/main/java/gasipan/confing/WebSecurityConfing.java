package gasipan.confing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import gasipan.service.UserService;

@EnableWebSecurity
public class WebSecurityConfing { 

	@Configuration
	@Order(1)
	public static class UserWebSecurityConfing extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private CustomAuthenticationProvider customAuthenticationProvider;
		
		@Bean
		public UserLoginSuccessHandler userLoginSuccessHandler() {
			return new UserLoginSuccessHandler("/");
		}
		
		/**
		 * 인증(authentication)이 필요한 url과 인증이 불필요한 url을 설정
		 * 권한이 필요한 url 설정
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			// http.authenticationProvider(null); 인증 성공, 인증 실패, 결정할 수 없음. 이 세 가지 시나리오에 대한 동작 설정
			
			http.requestMatchers()
					.antMatchers("/user/*")
					.and()
				.authorizeRequests()
					.antMatchers("/", "/joinView", "/user/loginPage").permitAll()
					.anyRequest().hasAnyRole("USER") // 권한 필요
					.and()
				.formLogin() // 로그인 화면 설정
					.loginPage("/user/loginPage")
					.usernameParameter("id")
					.passwordParameter("pwd")				
					.loginProcessingUrl("/user/login")
					.successHandler(userLoginSuccessHandler()) // 로그인 성공 이후의 동작 핸들링
					.failureUrl("/loginPage")// 로그인 화면 이동에 실패하면 가야할 경로
					.and()
				.logout()
					.permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.deleteCookies("JSESSIONID");
		}
		
		@Override
	    protected void configure(AuthenticationManagerBuilder authentication) {
	    	authentication.authenticationProvider(customAuthenticationProvider);
	    }
		
	}

	@Configuration
	public static class AdminWebSecurityConfing extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private CustomAuthenticationProvider customAuthenticationProvider;
		
		
		@Bean
		public AdminLoginSuccessHandler adminLoginSuccessHandler() {
			return new AdminLoginSuccessHandler();
		}
		 
		
		/**
		 * 인증(authentication)이 필요한 url과 인증이 불필요한 url을 설정
		 * 권한이 필요한 url 설정
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			// http.authenticationProvider(null); 인증 성공, 인증 실패, 결정할 수 없음. 이 세 가지 시나리오에 대한 동작 설정
			
			http.requestMatchers()
					.antMatchers("/admin/*")
				.and()
					.authorizeRequests()
					.antMatchers("/admin/loginPage").permitAll()
					.anyRequest().hasAuthority("ADMIN")
					//.anyRequest().access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin()
					.loginPage("/admin/loginPage")
					.usernameParameter("id")
					.passwordParameter("pwd")				
					.loginProcessingUrl("/admin/login")
					.successHandler(new AdminLoginSuccessHandler()) 
					.failureUrl("/admin/loginPage")
					//	.failureHandler(null) 로그인 실패 동작 핸들링
					.and()	
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");
		}
		
		@Override
	    protected void configure(AuthenticationManagerBuilder authentication) {
	    	authentication.authenticationProvider(customAuthenticationProvider);
	    }
		
	}

	
}
