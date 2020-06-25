package kr.or.ddit.mp.vo.reservation;

import java.io.Serializable;

public class ReservationVO implements Serializable{
	
	private String res_no;
	private String com_id;
	private String res_person;
	private String res_date;
	private String res_st;
	private String no_show;
	private String mem_no;
	
	//예약자명
	private String mem_name;
	
	//예약자 연락처
	private String mem_hp;
	
	//예약 시간
	private String res_time;

	//예약자 닉네임
	private String mem_nick;
	
	//업체이름
	private String com_name;
	
	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public String getRes_no() {
		return res_no;
	}

	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getRes_person() {
		return res_person;
	}

	public void setRes_person(String res_person) {
		this.res_person = res_person;
	}

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public String getRes_st() {
		return res_st;
	}

	public void setRes_st(String res_st) {
		this.res_st = res_st;
	}

	public String getNo_show() {
		return no_show;
	}

	public void setNo_show(String no_show) {
		this.no_show = no_show;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public ReservationVO(String res_no, String com_id, String res_person, String res_date, String res_st,
			String no_show, String mem_no, String mem_name, String mem_hp, String res_time, String mem_nick,
			String com_name) {
		super();
		this.res_no = res_no;
		this.com_id = com_id;
		this.res_person = res_person;
		this.res_date = res_date;
		this.res_st = res_st;
		this.no_show = no_show;
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_hp = mem_hp;
		this.res_time = res_time;
		this.mem_nick = mem_nick;
		this.com_name = com_name;
	}

	public ReservationVO() {
		
	}

}
