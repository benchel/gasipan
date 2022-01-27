package gasipan.dto;

import lombok.Data;

@Data
public class MembersDto {
	private String memberNum;
	private String memberId;
	private String memberPwd; 
	private String memberName; 
	private String memberEmail; 
	private String memberAddr; 
	private String regDate; 
	private String modifyDate;
}
