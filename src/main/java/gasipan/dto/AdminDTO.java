package gasipan.dto;

import lombok.Data;

@Data
public class AdminDTO {
	private String adminNum;
	private String adminId;
	private String adminPwd;
	private String adminName;	
	private String adminEmail;
	private String adminAddr;
	private String regDate;
	private String modifyDate;
}
