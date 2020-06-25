package kr.or.ddit.mp.vo.member;

import java.io.Serializable;

public class ComVO implements Serializable{

	private String com_id;
	private String mem_no;
	private String com_name;
	private String com_addr;
	private String com_photo;
	private String com_tel;
	private String com_cat;
	private String com_key;
	private String com_opentime;
	private String com_closetime;
	private String com_parking;
	private String com_menu;
	private String com_foodprice;
	

	/**
	 * 은혜 추가
	 */
	private String sumup;
	
	public ComVO(String com_id, String mem_no, String com_name, String com_addr, String com_photo, String com_tel,
			String com_cat, String com_key, String com_opentime, String com_closetime, String com_parking,
			String com_menu, String com_foodprice, String sumup) {
		super();
		this.com_id = com_id;
		this.mem_no = mem_no;
		this.com_name = com_name;
		this.com_addr = com_addr;
		this.com_photo = com_photo;
		this.com_tel = com_tel;
		this.com_cat = com_cat;
		this.com_key = com_key;
		this.com_opentime = com_opentime;
		this.com_closetime = com_closetime;
		this.com_parking = com_parking;
		this.com_menu = com_menu;
		this.com_foodprice = com_foodprice;
		this.sumup = sumup;
	}

	public String getSumup() {
		return sumup;
	}

	public void setSumup(String sumup) {
		this.sumup = sumup;
	}

	public ComVO() {
		// TODO Auto-generated constructor stub
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
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
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
	public String getCom_photo() {
		return com_photo;
	}
	public void setCom_photo(String com_photo) {
		this.com_photo = com_photo;
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
	
	
	
}
