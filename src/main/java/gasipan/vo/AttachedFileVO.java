package gasipan.vo;

import lombok.Data;

@Data
public class AttachedFileVO {
	private String fileKey;
	private String fileName;
	private String mimeType;
	private String parentType;
	private String parentId;	
	private String fileSize;
}
