package gasipan.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import gasipan.service.AdminDetailsServiceImp;
import gasipan.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminAuthenticationProvider implements AuthenticationProvider {

	private GasipanPasswordEncoder adminPasswordEncoder;
	private AdminDetailsServiceImp adminDetailsServiceImp;
	
	public AdminAuthenticationProvider(AdminDetailsServiceImp adminDetailsServiceImp) {
		this.adminDetailsServiceImp = adminDetailsServiceImp;
		this.adminPasswordEncoder = new GasipanPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();
		
		System.out.println("id : "+ id);
		System.out.println("pwd : " + pwd);
		
		try {
			AdminVO admin = adminDetailsServiceImp.loadUserByUsername(id);
			
			// 비밀번호 일치 여부 확인
			if(!adminPasswordEncoder.matches(pwd, admin.getPassword())) {
				throw new BadCredentialsException("a discrepancy between id and pwd");
			}
			
			ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(admin.getAuthority()));
			return new UsernamePasswordAuthenticationToken(admin, pwd, authorities);
			
		} catch (UsernameNotFoundException e) {
			throw new BadCredentialsException("not exist admin");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
