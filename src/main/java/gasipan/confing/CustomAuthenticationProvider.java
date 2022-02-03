package gasipan.confing;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import gasipan.dto.UsersDto;
import gasipan.service.UserService;
import gasipan.vo.UsersVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증과정 설정
 * @author benchel
 */
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 로그인 폼에서 입력한 정보를 가져온다.
		String id = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();
		pwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
		
		//System.out.println("id : " + id);
		//System.out.println("pwd : " + pwd);
		
		// DB에서 조회
		UsersVo userVo = userService.loadUserByUsername(id);
		
		if(userVo == null) 
			throw new BadCredentialsException("login error");
		
		//System.out.println("Authority : " + userVo.getAuthority());
		
		// 사용자 로그인인지 관리자 로그인인지 확인
		if(userVo.getAuthority().equals("USER")) {
			
			// 비밀번호 일치 여부 확인
			if(BCrypt.checkpw(userVo.getUserPwd(), pwd) )
				throw new BadCredentialsException("login error");
			
	        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority(userVo.getAuthority()));
	        return new UsernamePasswordAuthenticationToken(userVo, null, authorities);
	        
		} else if(userVo.getAuthority().equals("ADMIN")) {
			
			// 비밀번호 일치 여부 확인
			if(BCrypt.checkpw(userVo.getUserPwd(), pwd) )
				throw new BadCredentialsException("login error");
			
	        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority(userVo.getAuthority()));
	        return new UsernamePasswordAuthenticationToken(userVo, null, authorities);			
		} else {
			throw new BadCredentialsException("login error");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
