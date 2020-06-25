package kr.or.ddit.mp.vo.zipcode;

import java.io.Serializable;

public class ZipcodeVO implements Serializable {
	private String zipcode;
	private String dong;
	private String sido;
	private String gugun;
	
	
	public ZipcodeVO(String zipcode, String dong, String sido, String gugun) {
		super();
		this.zipcode = zipcode;
		this.dong = dong;
		this.sido = sido;
		this.gugun = gugun;
	}

	public ZipcodeVO() {
		
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
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	
	
}
