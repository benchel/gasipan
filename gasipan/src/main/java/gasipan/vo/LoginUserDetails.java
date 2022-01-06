package gasipan.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Spring Security Session에 저장될 오브젝트 타입 Authentication 
 * Authentication 은 UserDetails 구현체만 가능.
 */
public class LoginUserDetails implements UserDetails{
	
	private UsersVo user;
	
	public LoginUserDetails(UsersVo user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getAuthority();
			}
		});
		return collect;
	}
	
	public UsersVo getLoginUser() {
		return this.user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}
	
	public String getUserId() {
		// TODO Auto-generated method stub
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
