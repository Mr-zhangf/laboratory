package com.demo.entity;

public class Page {
	
	private int page;
	
	private int pageSize=10;
	
	private int num;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = page !=0? (this.page-1)*this.pageSize:num;
	}
	
	

}
