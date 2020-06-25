package kr.or.ddit.mp.vo.memInfoUpdate;

import java.io.Serializable;

public class ZiptbVO implements Serializable {
	public String zipcode;
	public String sido;
	public String gugun;
	public String dong;

	public ZiptbVO(String zipcode, String sido, String gugun, String dong) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
	}

	public ZiptbVO() {
		// TODO Auto-generated constructor stub
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

}
