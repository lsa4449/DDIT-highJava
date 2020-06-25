package kr.or.ddit.mp.vo.buylist;

import java.io.Serializable;

public class MemberVO implements Serializable{
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
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String mem_id, String mem_no, String mem_pw, String mem_name, String mem_nick, String mem_birth,
			String mem_mail, String mem_hp, String mem_addr, String melonage, String mem_grade) {
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
} 
