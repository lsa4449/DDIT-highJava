package kr.or.ddit.mp.vo.admin;

import java.io.Serializable;

public class BlackVO implements Serializable{

	private String mem_no;
	private String black_indate;
	
	public BlackVO() {
		// TODO Auto-generated constructor stub
	}
	
	public BlackVO(String mem_no, String black_indate) {
		super();
		this.mem_no = mem_no;
		this.black_indate = black_indate;
	}
	
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getBlack_indate() {
		return black_indate;
	}
	public void setBlack_indate(String black_indate) {
		this.black_indate = black_indate;
	}
	
	
}
