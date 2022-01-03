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
import gasipan.vo.UsersVo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 로그인 폼에서 입력한 정보를 가져온다.
		String id = (String) authentication.getPrincipal();
		
		UsersDto userInfor = new UsersDto();
		userInfor.setUserId(id);
		
		UsersVo user = userService.selectUserById(userInfor);
		
		if(user == null) 
			throw new BadCredentialsException("login error");
		
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
