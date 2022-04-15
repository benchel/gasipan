package gasipan.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * Spring Security Session에 저장될 오브젝트 타입 Authentication 
 * Authentication 은 UserDetails 구현체만 가능.
 */
@Data
public class UserVO implements UserDetails {

	// 직렬화 ID (Serializable ID)
	// 해당 객체의 버전을 명시하는 데 사용
	private static final long serialVersionUID = 1L;
	private static final String authority = "USER";
	
	private String userNum;
	private String userId;
	private String userPwd; 
	private String userName; 
	private String userEmail; 
	private String userAddr; 
	private String regDate;
	private String modifyDate;
	
	private String errorCode;
	
	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</code>.
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();    
		authorities.add(new SimpleGrantedAuthority(this.authority));
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.userPwd;
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getAuthority() {
		return this.authority;
	}

	/**
	 * 사용자의 계정이 만료되었는지 아닌지를 알린다. 유효 기간이 만료된 계정은 계정이 진정한 것인지 입증할 수 없다.
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 */	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 잠금 상태인 유저인지 아닌지를 알린다. 잠금된 유저는 인증할 수 없다. 
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 사용자의 자격이 유효한지 유효하지 않은 지를 알린다. 유효하지 않은 자격은 인증을 막는다.
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 * 
	 * 만약 사용자의 자격 증명이 유효한 경우 true, 더 이상 유효하지 않은 경우 false.
	 * @return <code>true</code> if the user's credentials are valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 활성화된 유저인지 비활성화된 유저인지 알린다. 비활성화된 유저는 인증할 수 없다.
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 * 활성화된 유저의 경우 true, 다른 기타의 경우 false
	 * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
