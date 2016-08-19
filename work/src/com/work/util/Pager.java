package com.work.util;

import java.util.List;

public class Pager<T> {
	
	private List<T> reusltLists;
	private int pageSize = 10;
	private int curPage = 1;
	private int totalPage;
	private int totalCount;
	
	public Pager(){
		
	}	
	public List<T> getReusltLists() {
		return reusltLists;
	}
	public void setReusltLists(List<T> reusltLists) {
		this.reusltLists = reusltLists;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
}
