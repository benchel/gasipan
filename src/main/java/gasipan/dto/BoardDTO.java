package gasipan.dto;

import lombok.Data;

@Data
public class BoardDTO {
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
