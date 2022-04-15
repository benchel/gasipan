package gasipan.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import gasipan.repository.UserDao;
import gasipan.service.UserDetailsServiceImp;
import gasipan.service.UserService;
import gasipan.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SiteAuthenticationProvider implements AuthenticationProvider {
	
	private GasipanPasswordEncoder sitePasswordEncoder;

	@Autowired
	private final UserDetailsServiceImp userDetailsServiceImp;
	
	public SiteAuthenticationProvider(UserDetailsServiceImp userDetailsServiceImp) {
		this.userDetailsServiceImp = userDetailsServiceImp;
		this.sitePasswordEncoder = new GasipanPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();
		
		System.out.println("id : "+ id);
		System.out.println("pwd : " + pwd);
		
		// DB에서 조회
		try {
			UserVO user = userDetailsServiceImp.loadUserByUsername(id);
			
			// 비밀번호 일치 여부 확인
			if(!sitePasswordEncoder.matches(pwd, user.getPassword())) {
				throw new BadCredentialsException("a discrepancy between id and pwd");
			}

			ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
			return new UsernamePasswordAuthenticationToken(user, pwd, authorities);
			
		} catch (NullPointerException e) {
			throw new BadCredentialsException("not exist user");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	} 

}
