package kr.or.ddit.mp.vo.comInfoUpdate;

import java.io.Serializable;

public class MemberVO implements Serializable {
	private String mem_id;
	private String mem_no;
	private String mem_pw;
	private String mem_name;
	private String mem_nick;
	private String mem_birth;
	private String mem_mail;
	private String mem_hp;
	private String mem_addr;
	private String melonage;
	private String mem_grade;
	private String mem_zipcode;
	// 추가된 항목 ㅈㅅ
	private String com_name;
	private String com_id;
	private String com_tel;
	private String count_review;
	private String sum_reUp;
	private String no_show;

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String mem_id, String mem_no, String mem_pw, String mem_name, String mem_nick, String mem_birth,
			String mem_mail, String mem_hp, String mem_addr, String melonage, String mem_grade, String mem_zipcode,
			String com_name, String com_id, String com_tel, String count_review, String sum_reUp, String no_show) {
		super();
		this.mem_id = mem_id;
		this.mem_no = mem_no;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_nick = mem_nick;
		this.mem_birth = mem_birth;
		this.mem_mail = mem_mail;
		this.mem_hp = mem_hp;
		this.mem_addr = mem_addr;
		this.melonage = melonage;
		this.mem_grade = mem_grade;
		this.mem_zipcode = mem_zipcode;
		this.com_name = com_name;
		this.com_id = com_id;
		this.com_tel = com_tel;
		this.count_review = count_review;
		this.sum_reUp = sum_reUp;
		this.no_show = no_show;
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

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
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

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getCom_tel() {
		return com_tel;
	}

	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}

	public String getCount_review() {
		return count_review;
	}

	public void setCount_review(String count_review) {
		this.count_review = count_review;
	}

	public String getSum_reUp() {
		return sum_reUp;
	}

	public void setSum_reUp(String sum_reUp) {
		this.sum_reUp = sum_reUp;
	}

	public String getNo_show() {
		return no_show;
	}

	public void setNo_show(String no_show) {
		this.no_show = no_show;
	}

	public String getMem_zipcode() {
		return mem_zipcode;
	}

	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

}
