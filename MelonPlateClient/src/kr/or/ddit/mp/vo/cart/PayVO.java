package kr.or.ddit.mp.vo.cart;

import java.io.Serializable;

public class PayVO implements Serializable {


	private String pay_no;
	private String order_no;
	private String pay_com;
	private String pay_price;
	private String pay_date;
	public PayVO() {
		// TODO Auto-generated constructor stub
	}
	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getPay_com() {
		return pay_com;
	}

	public void setPay_com(String pay_com) {
		this.pay_com = pay_com;
	}

	public String getPay_price() {
		return pay_price;
	}

	public void setPay_price(String pay_price) {
		this.pay_price = pay_price;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public PayVO(String pay_no, String order_no, String pay_com, String pay_price, String pay_date) {
		super();
		this.pay_no = pay_no;
		this.order_no = order_no;
		this.pay_com = pay_com;
		this.pay_price = pay_price;
		this.pay_date = pay_date;
	}
	
}
