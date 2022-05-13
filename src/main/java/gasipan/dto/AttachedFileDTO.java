package gasipan.dto;

import lombok.Data;

@Data
public class AttachedFileDTO {
	private String fileKey;
	private String fileName;
	private String mimeType;
	private String boardType;	
	private long size;	
}
