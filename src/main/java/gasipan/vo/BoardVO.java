package gasipan.vo;

import lombok.Data;

@Data
public class BoardVO extends BasicVO {
	private String fileName1;
	private String fileName2;
	private String fileName3;
	
	private long boardIdx;
	private long boardType;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String modifyDate;
	private String fileKey1;
	private String fileKey2;
	private String fileKey3;
}
