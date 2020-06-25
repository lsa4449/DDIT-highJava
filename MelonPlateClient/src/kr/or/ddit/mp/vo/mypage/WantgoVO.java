package kr.or.ddit.mp.vo.mypage;

import java.io.Serializable;

public class WantgoVO implements Serializable {
	
	private String wantgo_no;
	private String mem_no;
	private String com_name;
	private String com_addr;
	private String wantgo_indate;
	private String com_id;
	private String rownum;
	


	public WantgoVO(String wantgo_no, String mem_no, String com_name, String com_addr, String wantgo_indate,
			String com_id, String rownum) {
		super();
		this.wantgo_no = wantgo_no;
		this.mem_no = mem_no;
		this.com_name = com_name;
		this.com_addr = com_addr;
		this.wantgo_indate = wantgo_indate;
		this.com_id = com_id;
		this.rownum = rownum;
	}


	public String getRownum() {
		return rownum;
	}


	public void setRownum(String rownum) {
		this.rownum = rownum;
	}


	public String getCom_id() {
		return com_id;
	}


	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}


	public String getWantgo_indate() {
		return wantgo_indate;
	}


	public void setWantgo_indate(String wantgo_indate) {
		this.wantgo_indate = wantgo_indate;
	}


	public WantgoVO() {
		// TODO Auto-generated constructor stub
	}
	

	public String getWantgo_no() {
		return wantgo_no;
	}

	public void setWantgo_no(String wantgo_no) {
		this.wantgo_no = wantgo_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_addr() {
		return com_addr;
	}

	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}
	
}
