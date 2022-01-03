package gasipan.confing;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import gasipan.dto.UsersDto;
import gasipan.service.UserService;
import gasipan.vo.AdminVo;
import gasipan.vo.UsersVo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 로그인 폼에서 입력한 정보를 가져온다.
		String id = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();
		String authority = (String) authentication.getPrincipal();
		
		System.out.println("id : " + id);
		System.out.println("authority : " + authority);
		
		// 사용자 로그인인지 관리자 로그인인지 확인
		if(authority.equals("user")) {
			UsersDto userInfor = new UsersDto();
			userInfor.setUserId(id);
			
			// DB에서 조회
			UsersVo user = userService.selectUserById(userInfor);
			
			if(user == null) 
				throw new BadCredentialsException("login error");
			if(!user.getUserPwd().equals(pwd))
				throw new BadCredentialsException("login error");
				
	        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("USER"));
	        return new UsernamePasswordAuthenticationToken(user, null, authorities);
	        
		} else if(authority.equals("admin")) {
			
			AdminVo admin = null;
			
	        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ADMIN"));
	        return new UsernamePasswordAuthenticationToken(admin, null, authorities);			
		} else {
			throw new BadCredentialsException("login error");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
