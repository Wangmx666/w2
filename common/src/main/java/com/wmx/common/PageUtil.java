/** 
 * <pre>项目名称:common 
 * 文件名称:PageUtil.java 
 * 包名:com.wmx.common 
 * 创建日期:2020年1月10日下午2:49:00 
 * Copyright (c) 2020, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.wmx.common;

/** 
 * <pre>项目名称：common    
 * 类名称：PageUtil    
 * 类描述：    
 * 创建人：王明轩 chenjh123@gmail.com    
 * 创建时间：2020年1月10日 下午2:49:00    
 * 修改人：王明轩 chenjh123@gmail.com     
 * 修改时间：2020年1月10日 下午2:49:00    
 * 修改备注：       
 * @version </pre>    
 */
public class PageUtil {

	
	//layui每页条数
	private int limit = 5;
	//easyui每页条数
	private int rows = 5;
	//第几页
	private int page = 1;
	
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
}
