package gasipan.vo;

import lombok.Data;

@Data
public class UsersVo {
	private String userNum;
	private String userId;
	private String userPwd; 
	private String userName; 
	private String userEmail; 
	private String userAddr; 
	private String regDate;
	private String modifyDate;
	private final String authority;
}
