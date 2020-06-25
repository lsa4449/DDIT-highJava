package kr.or.ddit.mp.vo.eatdeal;

import java.io.Serializable;

public class SalesVO implements Serializable{
	
	private String rownum;
	private String eat_name;
	private String order_qty;
	private String order_price;
	private String eat_price;
	
	public SalesVO() {

	}
	
	public SalesVO(String rownum, String eat_name, String order_qty, String order_price, String eat_price) {
		super();
		this.rownum = rownum;
		this.eat_name = eat_name;
		this.order_qty = order_qty;
		this.order_price = order_price;
		this.eat_price = eat_price;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getEat_name() {
		return eat_name;
	}
	public void setEat_name(String eat_name) {
		this.eat_name = eat_name;
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
	public String getEat_price() {
		return eat_price;
	}
	public void setEat_price(String eat_price) {
		this.eat_price = eat_price;
	}

}
