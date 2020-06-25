package kr.or.ddit.mp.vo.mypage;

import java.io.Serializable;

public class ZoneVO implements Serializable {
	private String mem_no;
	private String zone1;
	private String zone2;
	private String zone3;
	public ZoneVO() {
		// TODO Auto-generated constructor stub
	}
	public ZoneVO(String mem_no, String zone1, String zone2, String zone3) {
		super();
		this.mem_no = mem_no;
		this.zone1 = zone1;
		this.zone2 = zone2;
		this.zone3 = zone3;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getZone1() {
		return zone1;
	}

	public void setZone1(String zone1) {
		this.zone1 = zone1;
	}

	public String getZone2() {
		return zone2;
	}

	public void setZone2(String zone2) {
		this.zone2 = zone2;
	}

	public String getZone3() {
		return zone3;
	}

	public void setZone3(String zone3) {
		this.zone3 = zone3;
	}
	
}
