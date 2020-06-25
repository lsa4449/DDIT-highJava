package kr.or.ddit.mp.vo.buylist;

import java.io.Serializable;

public class RequestVO implements Serializable {

	private String order_no;
	private String cart_id;
	private String eat_name;
	private String eat_cont;
	private String order_qty;
	private String order_price;
	private String order_date;
	private String melonage;
	private String pay_no;
	private String order_st;
	private String mem_no;
	private String eat_no;
	private String rownum;
	
	public RequestVO() {
		
	}

	public RequestVO(String order_no, String cart_id, String eat_name, String eat_cont, String order_qty,
			String order_price, String order_date, String melonage, String pay_no, String order_st, String mem_no,
			String eat_no, String rownum) {
		super();
		this.order_no = order_no;
		this.cart_id = cart_id;
		this.eat_name = eat_name;
		this.eat_cont = eat_cont;
		this.order_qty = order_qty;
		this.order_price = order_price;
		this.order_date = order_date;
		this.melonage = melonage;
		this.pay_no = pay_no;
		this.order_st = order_st;
		this.mem_no = mem_no;
		this.eat_no = eat_no;
		this.rownum = rownum;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}

	public String getEat_name() {
		return eat_name;
	}

	public void setEat_name(String eat_name) {
		this.eat_name = eat_name;
	}

	public String getEat_cont() {
		return eat_cont;
	}

	public void setEat_cont(String eat_cont) {
		this.eat_cont = eat_cont;
	}

	public String getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(String order_qty) {
		this.order_qty = order_qty;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getMelonage() {
		return melonage;
	}

	public void setMelonage(String melonage) {
		this.melonage = melonage;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getOrder_st() {
		return order_st;
	}

	public void setOrder_st(String order_st) {
		this.order_st = order_st;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getEat_no() {
		return eat_no;
	}

	public void setEat_no(String eat_no) {
		this.eat_no = eat_no;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRow_num(String rownum) {
		this.rownum = rownum;
	}

	
	


}
