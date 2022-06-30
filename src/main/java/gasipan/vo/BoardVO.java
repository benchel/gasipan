package gasipan.vo;

import lombok.Data;

@Data
public class BoardVO extends BasicVO {	
	private long boardIdx;
	private String boardType;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String modifyDate;
	private String fileKey1;
	private String fileKey2;
	private String fileKey3;
}
