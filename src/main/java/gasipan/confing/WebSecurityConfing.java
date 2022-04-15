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

import gasipan.bean.AdminAccessDeniedHandlerImp;
import gasipan.bean.AdminAuthenticationProvider;
import gasipan.bean.AdminLoginSuccessHandler;
import gasipan.bean.GasipanPasswordEncoder;
import gasipan.bean.SiteAuthenticationProvider;
import gasipan.bean.UserAccessDeniedHandlerImp;
import gasipan.bean.UserFailureHandler;
import gasipan.bean.UserLoginSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfing { 

	@Configuration
	@Order(1)
	public static class UserWebSecurityConfing extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private SiteAuthenticationProvider siteAuthenticationProvider;
		
		@Bean
		public UserLoginSuccessHandler userLoginSuccessHandler() {
			return new UserLoginSuccessHandler();
		}
		
		@Bean
		public UserAccessDeniedHandlerImp userAccessDeniedHandlerImp() {
			return new UserAccessDeniedHandlerImp();
		}
		
		
		public UserFailureHandler userFailureHandler() {
			return new UserFailureHandler();
		}
		 
		@Bean
		public GasipanPasswordEncoder sitePasswordEncoder() {
			return new GasipanPasswordEncoder();
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
					.antMatchers("/user/myPage").authenticated()
					.antMatchers("/user/myPage").hasAuthority("USER") // 권한 필요
					.anyRequest().permitAll()
					.and()
				.exceptionHandling()
					.accessDeniedHandler(new UserAccessDeniedHandlerImp()) // 권한이 없는 사용자의 접근이 있을 때의 동작 핸들링
					.and()
				.formLogin() // 로그인 화면 설정
					.loginPage("/user/loginPage")
					.usernameParameter("id")
					.passwordParameter("pwd")				
					.loginProcessingUrl("/user/login")
					.successHandler(userLoginSuccessHandler()) // 로그인 성공 이후의 동작 핸들링
					.failureHandler(userFailureHandler())
					//.failureUrl("/user/loginPage")// 로그인 화면 이동에 실패하면 가야할 경로
					.and()
				.logout()
					.permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/user/loginPage")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");
		}
		
		@Override
	    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
	    	authentication.authenticationProvider(siteAuthenticationProvider);
	    }
		
	}

	@Configuration
	public static class AdminWebSecurityConfing extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private AdminAuthenticationProvider adminAuthenticationProvider;
		
		@Bean
		public AdminLoginSuccessHandler adminLoginSuccessHandler() {
			return new AdminLoginSuccessHandler();
		}

		@Bean
		public AdminAccessDeniedHandlerImp accessDeniedHandler() {
			return new AdminAccessDeniedHandlerImp();
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
					.antMatchers("/admin/*").authenticated()
					.antMatchers("/admin/*").hasAuthority("ADMIN")
					.and()
				.exceptionHandling()
					.accessDeniedHandler(new AdminAccessDeniedHandlerImp()) // 권한이 없는 사용자의 접근 핸들링
					.and()
				.formLogin()
					.loginPage("/admin/loginPage")
					.usernameParameter("id")
					.passwordParameter("pwd")				
					.loginProcessingUrl("/admin/login")
					.successHandler(new AdminLoginSuccessHandler()) 
					.failureUrl("/admin/loginPage") // 로그인 실패 동작 핸들링
					.and()	
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
					.logoutSuccessUrl("/admin/loginPage")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");
		}
		
		@Override
	    protected void configure(AuthenticationManagerBuilder authentication) {
	    	authentication.authenticationProvider(adminAuthenticationProvider);
	    }
		
	}

	
}
