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
	
	/**
	 * 페이지네이션
	 * @param totalCount 게시글 총 개수
	 * @param pageNo 페이지번호
	 * @param pageSize 
	 * @param pageBlock
	 * @return
	 */
	public static String getPagingHTML(long totalCount, long pageNo, long pageSize, long pageBlock) {
		// String :  불변클래스(인스턴스가 한 번 생성되면 그 값을 변경할 수 없는 클래스) 
		// 덧셈(+) 연산자를 이용해 String 인스턴스의 문자열을 결합하면, 내용이 합쳐진 새로운 String 인스턴스를 생성. 비효율적
		
		StringBuffer rs = new StringBuffer();
		
		long totalPage = getTotalPageNo(totalCount, pageSize);

		if (totalCount > 0) {
			// 현재 페이지 계산
			long startPage = 1;
			if (pageNo > 0 && pageBlock > 0) {
				if (pageNo % pageBlock > 0) {
					startPage = pageNo - (pageNo % pageBlock) + 1;
				} else {
					startPage = pageNo - pageBlock + 1;
				}
			}

			// 마지막 페이지 계산
			long endPage = startPage + pageBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage; // 마지막 페이지가 총 페이지 수 보다 크면 총 페이지로 셋팅
			}

			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}
			
			String prevActive = "";
			if (beforePage == 1) {
				prevActive = "disabled";
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}
			
			rs.setLength(0);

			rs.append("	<ul class=\"numbers-of-page\">	\n");

			// 이전
			rs.append(" <li class=\"page-num\" data-pageNo=\""+ beforePage +"\">◁</li> ");
			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				if (pageNo == i) {
					rs.append(" <li class=\"page-num active\"> " + i + " </li> ");
				} else {
					rs.append(" <li class=\"page-num\" data-pageNo=\""+ i +"\"> " + i + " </li>");
				}
			}

			// 다음
			rs.append(" <li class=\"page-num\">▷</li>");
			rs.append("	</ul>");
		}
		
		/**
    	<ul class="numbers-of-page">
			<li class="page-num">◁</li>
			<li class="page-num">1</li>
			<li class="page-num">2</li>
			<li class="page-num">3</li>
			<li class="page-num">4</li>
			<li class="page-num">5</li>
			<li class="page-num">6</li>
			<li class="page-num">7</li>
			<li class="page-num">8</li>
			<li class="page-num">9</li>
			<li class="page-num">10</li>
			<li class="page-num">▷</li>
		</ul>*/
		
		return rs.toString();
	}
	
	
	public static String getAdminPagingHTML() {
		return "";
	}
	
}
