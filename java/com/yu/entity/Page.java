package com.yu.entity;

public class Page {
	private int pageNow; // 当前页数 ,未传参数默认当前页数为1

	private int pageSize; // 每页显示记录的条数

	private int totalCount; // 总的记录条数

	private int totalPageCount; // 总的页数

	private int startPos; // 查询偏移量

	@Override
	public String toString() {
		return "Page [pageNow=" + pageNow + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPageCount=" + totalPageCount + ", startPos=" + startPos + "]";
	}

	public Page() {
		super();
	}

	public Page(int pageNow, int pageSize, int totalCount, int totalPageCount, int startPos) {
		super();
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPageCount = totalPageCount;
		this.startPos = startPos;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

}
