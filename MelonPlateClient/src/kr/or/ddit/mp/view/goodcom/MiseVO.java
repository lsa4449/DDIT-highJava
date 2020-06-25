package kr.or.ddit.mp.view.goodcom;

public class MiseVO {
	private String pm10Value;
	private String pm10Value24;
	private String pm25Value;
	private String pm25Value24;
	private String pm10Grade;
	private String pm25Grade;
	
	public MiseVO() {
		
	}
	
	public MiseVO(String pm10Value, String pm10Value24, String pm25Value, String pm25Value24, String pm10Grade,
			String pm25Grade) {
		super();
		this.pm10Value = pm10Value;
		this.pm10Value24 = pm10Value24;
		this.pm25Value = pm25Value;
		this.pm25Value24 = pm25Value24;
		this.pm10Grade = pm10Grade;
		this.pm25Grade = pm25Grade;
	}
	public String getPm10Value() {
		return pm10Value;
	}
	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}
	public String getPm10Value24() {
		return pm10Value24;
	}
	public void setPm10Value24(String pm10Value24) {
		this.pm10Value24 = pm10Value24;
	}
	public String getPm25Value() {
		return pm25Value;
	}
	public void setPm25Value(String pm25Value) {
		this.pm25Value = pm25Value;
	}
	public String getPm25Value24() {
		return pm25Value24;
	}
	public void setPm25Value24(String pm25Value24) {
		this.pm25Value24 = pm25Value24;
	}
	public String getPm10Grade() {
		return pm10Grade;
	}
	public void setPm10Grade(String pm10Grade) {
		this.pm10Grade = pm10Grade;
	}
	public String getPm25Grade() {
		return pm25Grade;
	}
	public void setPm25Grade(String pm25Grade) {
		this.pm25Grade = pm25Grade;
	}

//	등급	좋음	보통	나쁨	매우나쁨
//	Grade 값	1	2	3	4
//	
}
