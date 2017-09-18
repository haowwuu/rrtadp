package com.rrt.adp.model;

import java.util.List;


/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月18日
 * 
 * @param <T>
 */
public class Page<T> {

    private int pageNum;    
    private int pageSize; 
    private int total;

    private List<T> list;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
