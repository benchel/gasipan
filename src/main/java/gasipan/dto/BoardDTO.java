package gasipan.dto;

import lombok.Data;

@Data
public class BoardDTO extends BasicDTO {
	private long boardIdx;
	private String boardType;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String modifyDate;
}
