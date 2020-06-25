package kr.or.ddit.mp.vo.cart;

import java.io.Serializable;

public class CartVO implements Serializable{

	private String cart_no;
	private String eat_no;
	private String eatdeal_name;
	private String cart_price;
	private String cart_qty;
	private String mem_no;
	private String in_date;
	private String rownum;
	
	
	public CartVO() {
		
	}


	public CartVO(String cart_no, String eat_no, String eatdeal_name, String cart_price, String cart_qty, String mem_no,
			String in_date, String rownum) {
		super();
		this.cart_no = cart_no;
		this.eat_no = eat_no;
		this.eatdeal_name = eatdeal_name;
		this.cart_price = cart_price;
		this.cart_qty = cart_qty;
		this.mem_no = mem_no;
		this.in_date = in_date;
		this.rownum = rownum;
	}


	public String getCart_no() {
		return cart_no;
	}


	public void setCart_no(String cart_no) {
		this.cart_no = cart_no;
	}


	public String getEat_no() {
		return eat_no;
	}


	public void setEat_no(String eat_no) {
		this.eat_no = eat_no;
	}


	public String getEatdeal_name() {
		return eatdeal_name;
	}


	public void setEatdeal_name(String eatdeal_name) {
		this.eatdeal_name = eatdeal_name;
	}


	public String getCart_price() {
		return cart_price;
	}


	public void setCart_price(String cart_price) {
		this.cart_price = cart_price;
	}


	public String getCart_qty() {
		return cart_qty;
	}


	public void setCart_qty(String cart_qty) {
		this.cart_qty = cart_qty;
	}


	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public String getIn_date() {
		return in_date;
	}


	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}


	public String getRownum() {
		return rownum;
	}


	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
}
