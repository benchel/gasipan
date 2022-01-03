package gasipan.dto;

import lombok.Data;

@Data
public class UsersDto {
	private String userNum;
	private String userId;
	private String userPwd; 
	private String userName; 
	private String userEmail; 
	private String userAddr; 
	private String regDate;
	private String modifyDate;
}
