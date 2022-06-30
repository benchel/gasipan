package gasipan.dto;

import lombok.Data;

@Data
public class AttachedFileDTO {
	private String fileKey;
	private String fileName;
	private String mimeType;
	private String parentType;
	private long parentId;	
	private long fileSize;
}
