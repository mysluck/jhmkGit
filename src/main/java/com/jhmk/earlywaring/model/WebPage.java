package com.jhmk.earlywaring.model;

/**
 * 页面Page信息
 * @author zzy
 *
 */
public class WebPage {
	public static final String WEB_PAGE = "webPage";
	public static final String PAGE_NUM="pageNum";
	
	private static int DEFAULT_PAGE_SIZE = 18	;
	
	private int pageNo = 1;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private long totalCount;
	private int totalPageNum;
	private boolean hasPre = false;
	private boolean hasNext = false;
	
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public boolean isHasPre() {
		return hasPre;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	
	/**
	 * 取得下页的页号,序号从1开始.
	 */
	public int getNextPage() {
		if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
	}
	/**
	 * 取得上页的页号,序号从1开始.
	 */
	public int getPrePage() {
		if (isHasPre()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
	}


}
