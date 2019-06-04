package com.app.utils;

public class Pages {
	/**
	 * 记录条数
	 */
	private static int count;
	/**
	 * 根据查询到的用户记录数，一页显示10条记录，计算到的页面数
	 */
	private static int totalPageCount = 0;
	{

	}

	/**
	 * 根据传进来的记录条数，查询到
	 * 
	 * @param map
	 *            查询的条件
	 * @return 返回查询到的记录条数count
	 */
	public static final int getPageCount(int pageCount) {
		count = pageCount;
		mo();
		return totalPageCount;
	}

	public static final void mo() {
		if (count > 10) {
			if (count % 10 == 0) {
				totalPageCount = count / 5;
			} else {
				totalPageCount = (count / 5) + 1;
			}
		} else if (count <= 10 && count > 0) {
			totalPageCount = 1;
		}
	}
}
