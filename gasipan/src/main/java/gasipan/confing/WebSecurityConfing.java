package gasipan.confing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfing extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	/**
	 * 인증(authentication)이 필요한 url과 인증이 불필요한 url을 설정
	 * 권한이 필요한 url 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// http.authenticationProvider(null); 인증 성공, 인증 실패, 결정할 수 없음. 이 세 가지 시나리오에 대한 동작 설정
		
		http.authorizeRequests()
				.antMatchers("/myPage").hasAnyRole("USER") // 권한 필요
				.antMatchers("/admin/*").hasAnyRole("ADMIN") // 권한 필요
				.anyRequest().permitAll()
				.and()
			.formLogin() // 로그인 화면 설정
				.loginPage("/loginPage")
				.usernameParameter("id")
				.passwordParameter("pwd")				
				.loginProcessingUrl("/login")
				.successHandler(new LoginSuccessHandler("/")) // 로그인 성공 이후의 동작 핸들링
				.failureUrl("/loginPage")// 로그인 화면 이동에 실패하면 가야할 경로
				.loginPage("/admin/loginPage");
		/* 
				.permitAll()
				.usernameParameter("id")
				.passwordParameter("pwd")				
				.loginProcessingUrl("/login")
				.successHandler(new LoginSuccessHandler("/admin/index")) // 로그인 성공 이후의 동작 핸들링
				.failureUrl("/admin/loginPage"); // 로그인 화면 이동에 실패하면 가야할 경로
				// .failureHandler(null) 로그인 실패 동작 핸들링
		*/
		http.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("JSESSIONID");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder authentication) {
    	authentication.authenticationProvider(customAuthenticationProvider);
    }
	
}
