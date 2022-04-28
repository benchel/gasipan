package gasipan.util;

public class GasipanPagingUtil {

	/**
	 * 총 페이지 수 구하기
	 * @param totalCount 총 게시글수
	 * @param pageSize 한 페이지당 나와야할 게시글의 개수
	 * @return
	 */
	public static long getTotalPageNo(long totalCount, long pageSize) {
		long totalPageNo = 1L;
		
		if(totalCount > 0 && pageSize > 0) {
			totalPageNo = (long) Math.ceil((double) totalCount / pageSize);
		}
		
		return totalPageNo;
	}
	
	/**
	 * 페이지 시작 번호 구하기
	 * @param pageNo 페이지 번호
	 * @param pageSize 한 페이지당 나와야할 게시글의 개수
	 * @return
	 */
	public static long getPageStartNo(long pageNo, long pageSize) {
		return pageSize * (pageNo - 1);
	}
	
	/**
	 * 페이지 끝 번호 구하기
	 * @param pageNo 페이지 번호
	 * @param pageSize 한 페이지당 나와야할 게시글의 개수
	 * @return
	 */
	public static long getPageEndNo(long pageNo, long pageSize) {
		return getPageStartNo(pageNo, pageSize) + pageSize;
	}
	
}
