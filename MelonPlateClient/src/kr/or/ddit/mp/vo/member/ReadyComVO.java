package kr.or.ddit.mp.vo.member;

import java.io.Serializable;

public class ReadyComVO implements Serializable {

	private String mem_id;
	private String mem_no;
	private String mem_pw;
	private String mem_name;
	private String mem_nick;
	private String mem_birth;
	private String mem_hp;
	private String mem_addr;
	private String melonage;
	private String mem_grade;
	private String mem_email;
	private String mem_zipcode;
	private String com_id;
	private String com_name;
	private String com_addr;
	private String com_tel;
	private String com_cat;
	private String com_key;
	private String com_opentime;
	private String com_closetime;
	private String com_parking;
	private String com_menu;
	private String com_foodprice;
	private String com_photo;
	// 추가
	private String com_zipcode;

	public ReadyComVO() {
		// TODO Auto-generated constructor stub
	}

	public ReadyComVO(String mem_id, String mem_no, String mem_pw, String mem_name, String mem_nick, String mem_birth,
			String mem_hp, String mem_addr, String melonage, String mem_grade, String mem_email, String mem_zipcode,
			String com_id, String com_name, String com_addr, String com_tel, String com_cat, String com_key,
			String com_opentime, String com_closetime, String com_parking, String com_menu, String com_foodprice,
			String com_photo, String com_zipcode) {
		super();
		this.mem_id = mem_id;
		this.mem_no = mem_no;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_nick = mem_nick;
		this.mem_birth = mem_birth;
		this.mem_hp = mem_hp;
		this.mem_addr = mem_addr;
		this.melonage = melonage;
		this.mem_grade = mem_grade;
		this.mem_email = mem_email;
		this.mem_zipcode = mem_zipcode;
		this.com_id = com_id;
		this.com_name = com_name;
		this.com_addr = com_addr;
		this.com_tel = com_tel;
		this.com_cat = com_cat;
		this.com_key = com_key;
		this.com_opentime = com_opentime;
		this.com_closetime = com_closetime;
		this.com_parking = com_parking;
		this.com_menu = com_menu;
		this.com_foodprice = com_foodprice;
		this.com_photo = com_photo;
		this.com_zipcode = com_zipcode;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public String getMelonage() {
		return melonage;
	}

	public void setMelonage(String melonage) {
		this.melonage = melonage;
	}

	public String getMem_grade() {
		return mem_grade;
	}

	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_zipcode() {
		return mem_zipcode;
	}

	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
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

	public String getCom_tel() {
		return com_tel;
	}

	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}

	public String getCom_cat() {
		return com_cat;
	}

	public void setCom_cat(String com_cat) {
		this.com_cat = com_cat;
	}

	public String getCom_key() {
		return com_key;
	}

	public void setCom_key(String com_key) {
		this.com_key = com_key;
	}

	public String getCom_opentime() {
		return com_opentime;
	}

	public void setCom_opentime(String com_opentime) {
		this.com_opentime = com_opentime;
	}

	public String getCom_closetime() {
		return com_closetime;
	}

	public void setCom_closetime(String com_closetime) {
		this.com_closetime = com_closetime;
	}

	public String getCom_parking() {
		return com_parking;
	}

	public void setCom_parking(String com_parking) {
		this.com_parking = com_parking;
	}

	public String getCom_menu() {
		return com_menu;
	}

	public void setCom_menu(String com_menu) {
		this.com_menu = com_menu;
	}

	public String getCom_foodprice() {
		return com_foodprice;
	}

	public void setCom_foodprice(String com_foodprice) {
		this.com_foodprice = com_foodprice;
	}

	public String getCom_photo() {
		return com_photo;
	}

	public void setCom_photo(String com_photo) {
		this.com_photo = com_photo;
	}

	public String getCom_zipcode() {
		return com_zipcode;
	}

	public void setCom_zipcode(String com_zipcode) {
		this.com_zipcode = com_zipcode;
	}

}
