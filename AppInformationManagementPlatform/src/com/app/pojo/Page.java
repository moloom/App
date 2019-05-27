package com.app.pojo;

public class Page {

	private Integer totalCount;
	private Integer currentPageNo;
	private Integer totalPageCount;

	public Integer getTotalCount() {
		return totalCount;
	}

//	当设置多少条记录时，计算有几页，并保存到totalPageCount
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		if (totalCount > 10) {
			if (totalCount % 10 == 0) {
				this.totalPageCount = totalCount / 10;
			} else {
				this.totalPageCount = (totalCount / 10) + 1;
			}
		} else if (totalCount <= 10 && totalCount > 0) {
			this.totalPageCount = 1;
		}
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Integer getTotalPageCount() {

		return totalPageCount;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	@Override
	public String toString() {
		return "Page [totalCount=" + totalCount + ", currentPageNo=" + currentPageNo + ", totalPageCount="
				+ totalPageCount + "]";
	}
}
