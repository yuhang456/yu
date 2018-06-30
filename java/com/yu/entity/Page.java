package com.yu.entity;

public class Page {
	private int pageNow; // ��ǰҳ�� ,δ������Ĭ�ϵ�ǰҳ��Ϊ1

	private int pageSize; // ÿҳ��ʾ��¼������

	private int totalCount; // �ܵļ�¼����

	private int totalPageCount; // �ܵ�ҳ��

	private int startPos; // ��ѯƫ����

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
