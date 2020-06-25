package kr.or.ddit.buyer.vo;

public class BuyerVO {

	private String buyer_id;   
	private String buyer_name; 
	private String buyer_lgu;  
	private String buyer_mail; 
	private String buyer_zip;  
	private String buyer_add1; 
	private String buyer_add2; 
	private String buyer_comtel;
	private String buyer_fax;  
                               
	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_lgu() {
		return buyer_lgu;
	}

	public void setBuyer_lgu(String buyer_lgu) {
		this.buyer_lgu = buyer_lgu;
	}

	public String getBuyer_mail() {
		return buyer_mail;
	}

	public void setBuyer_mail(String buyer_mail) {
		this.buyer_mail = buyer_mail;
	}

	public String getBuyer_zip() {
		return buyer_zip;
	}

	public void setBuyer_zip(String buyer_zip) {
		this.buyer_zip = buyer_zip;
	}

	public String getBuyer_add1() {
		return buyer_add1;
	}

	public void setBuyer_add1(String buyer_add1) {
		this.buyer_add1 = buyer_add1;
	}

	public String getBuyer_add2() {
		return buyer_add2;
	}

	public void setBuyer_add2(String buyer_add2) {
		this.buyer_add2 = buyer_add2;
	}

	public String getBuyer_comtel() {
		return buyer_comtel;
	}

	public void setBuyer_comtel(String buyer_comtel) {
		this.buyer_comtel = buyer_comtel;
	}

	public String getBuyer_fax() {
		return buyer_fax;
	}

	public void setBuyer_fax(String buyer_fax) {
		this.buyer_fax = buyer_fax;
	}

	@Override
	public String toString() {
		return "BuyerVO [buyer_id=" + buyer_id + ", buyer_name=" + buyer_name + ", buyer_lgu=" + buyer_lgu
				+ ", buyer_mail=" + buyer_mail + ", buyer_zip=" + buyer_zip + ", buyer_add1=" + buyer_add1
				+ ", buyer_add2=" + buyer_add2 + ", buyer_comtel=" + buyer_comtel + ", buyer_fax=" + buyer_fax + "]";
	}

}
