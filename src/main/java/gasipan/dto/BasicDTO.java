package gasipan.dto;

import java.util.List;

public class BasicDTO {
	//페이징
	private Long pageNo = 1L;//페이지 번호
	private Long pageSize = 10L;//페이지 사이즈
	private Long pageBlock = 10L;//페이지 블록
	private Long pageOffset;//페이징 범위

	//검색
	private String searchField;//검색 필드
	private String searchKwd;//검색 단어
	
	// 파일키
	private List<String> fileKeyList;
	
	public BasicDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
	
	public Long getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	
	public Long getPageBlock() {
		return pageBlock;
	}
	
	public void setPageBlock(Long pageBlock) {
		this.pageBlock = pageBlock;
	}
	
	public Long getPageOffset() {
		return pageOffset;
	}
	
	public void setPageOffset(Long pageOffset) {
		this.pageOffset = pageOffset;
	}

	public String getSearchField() {
		return searchField;
	}
	
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	
	}
	public String getSearchKwd() {
		return searchKwd;
	}
	
	public void setSearchKwd(String searchKwd) {
		this.searchKwd = searchKwd;
	}

	public List<String> getFileKeyList() {
		return fileKeyList;
	}

	public void setFileKeyList(List<String> fileKeyList) {
		this.fileKeyList = fileKeyList;
	}
	
	
}
