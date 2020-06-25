package kr.or.ddit.basic.vo;

public class zipVO {

	// zipcode varchar2(7) not null, --우편번호 7
	// sido varchar2(6) not null, -- 특별시,광역시,도 4
	// gugun varchar2(30), -- 시,군,구 17
	// dong varchar2(40), --읍,면,동 26
	// ri varchar2(30), -- 리명 18
	// bldg varchar2(60), -- 건물명 40
	// bunji varchar2(30), -- 번지,아파트동,호수 17
	// seq number(5) not null, -- 데이터 순서 5

	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;

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

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

}
