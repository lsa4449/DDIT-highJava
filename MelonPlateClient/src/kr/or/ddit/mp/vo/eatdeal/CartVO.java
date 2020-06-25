package kr.or.ddit.mp.vo.eatdeal;

import java.io.Serializable;

public class CartVO implements Serializable{

	private String cart_id;
	private String eat_no;
	private String cart_price;
	private String cart_qty;
	private String order_no;
	private String mem_no;
	private String in_date;
	private String eatdeal_name;
	
	public CartVO(String cart_id, String eat_no, String cart_price, String cart_qty, String order_no, String mem_no,
			String in_date, String eatdeal_name) {
		super();
		this.cart_id = cart_id;
		this.eat_no = eat_no;
		this.cart_price = cart_price;
		this.cart_qty = cart_qty;
		this.order_no = order_no;
		this.mem_no = mem_no;
		this.in_date = in_date;
		this.eatdeal_name = eatdeal_name;
	}


	public String getEatdeal_name() {
		return eatdeal_name;
	}


	public void setEatdeal_name(String eatdeal_name) {
		this.eatdeal_name = eatdeal_name;
	}


	public CartVO() {
		// TODO Auto-generated constructor stub
	}
	

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}

	public String getEat_no() {
		return eat_no;
	}

	public void setEat_no(String eat_no) {
		this.eat_no = eat_no;
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

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	
	
}
