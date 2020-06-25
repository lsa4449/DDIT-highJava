package kr.or.ddit.mp.vo.admin;

import java.io.Serializable;

public class HolicVO implements Serializable{
	
	private String mem_no;
	private String up_qty;
	private String re_qty;
	private String hol_date;
	
	public HolicVO() {
		// TODO Auto-generated constructor stub
	}
	
	public HolicVO(String mem_no, String up_qty, String re_qty, String hol_date) {
		super();
		this.mem_no = mem_no;
		this.up_qty = up_qty;
		this.re_qty = re_qty;
		this.hol_date = hol_date;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getUp_qty() {
		return up_qty;
	}
	public void setUp_qty(String up_qty) {
		this.up_qty = up_qty;
	}
	public String getRe_qty() {
		return re_qty;
	}
	public void setRe_qty(String re_qty) {
		this.re_qty = re_qty;
	}
	public String getHol_date() {
		return hol_date;
	}
	public void setHol_date(String hol_date) {
		this.hol_date = hol_date;
	}
}
