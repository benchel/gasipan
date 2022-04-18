package gasipan.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AdminVO implements UserDetails {

	private static final long serialVersionUID = 1L;
	private static final String authority = "ADMIN";
	
	private String adminNum;
	private String adminId;
	private String adminPwd;
	private String adminName;	
	private String adminEmail;
	private String adminAddr;
	private String regDate;
	private String modifyDate;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.adminPwd;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.adminName;
	}
	
	public String getAdminId() {
		return this.adminId;
	}
	
	public String getAuthority() {
		return this.authority;
	}	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
