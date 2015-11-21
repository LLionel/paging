package cn.Lionel.cstm.domain;

import java.util.List;

public class PageBean<T> {
	
	private int pc ; // 当前页面
	private int tr ; //total record 总记录数
	private int ps ; // page size  每页记录数
	private List<T> beanList ; //当前页的数据
	private String url ; 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	/**
	 * 
	 * 得到total pages;
	 * @param tp
	 */
	public int getTp()
	{
		int tp = tr/ps ;
		return tr%ps==0 ? tp : tp+1 ;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

}
