package kr.or.ddit.mp.vo.recogeatdeal;

import java.io.Serializable;

public class EatdealVO implements Serializable{
	
	private String eat_no;
	private String eat_name;
	private String eat_cont1;
	private String eat_price;
	private String eat_qty;
	private String in_date;
	private String eat_approve;
	private String com_id;
	private String eat_cont2;
	private String eat_del;
	private String com_name;
	private String eat_sta;
	
	
	public EatdealVO() {
		
	}


	public EatdealVO(String eat_no, String eat_name, String eat_cont1, String eat_price, String eat_qty, String in_date,
			String eat_approve, String com_id, String eat_cont2, String eat_del, String com_name, String eat_sta) {
		super();
		this.eat_no = eat_no;
		this.eat_name = eat_name;
		this.eat_cont1 = eat_cont1;
		this.eat_price = eat_price;
		this.eat_qty = eat_qty;
		this.in_date = in_date;
		this.eat_approve = eat_approve;
		this.com_id = com_id;
		this.eat_cont2 = eat_cont2;
		this.eat_del = eat_del;
		this.com_name = com_name;
		this.eat_sta = eat_sta;
	}


	public String getEat_no() {
		return eat_no;
	}


	public void setEat_no(String eat_no) {
		this.eat_no = eat_no;
	}


	public String getEat_name() {
		return eat_name;
	}


	public void setEat_name(String eat_name) {
		this.eat_name = eat_name;
	}


	public String getEat_cont1() {
		return eat_cont1;
	}


	public void setEat_cont1(String eat_cont1) {
		this.eat_cont1 = eat_cont1;
	}


	public String getEat_price() {
		return eat_price;
	}


	public void setEat_price(String eat_price) {
		this.eat_price = eat_price;
	}


	public String getEat_qty() {
		return eat_qty;
	}


	public void setEat_qty(String eat_qty) {
		this.eat_qty = eat_qty;
	}


	public String getIn_date() {
		return in_date;
	}


	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}


	public String getEat_approve() {
		return eat_approve;
	}


	public void setEat_approve(String eat_approve) {
		this.eat_approve = eat_approve;
	}


	public String getCom_id() {
		return com_id;
	}


	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}


	public String getEat_cont2() {
		return eat_cont2;
	}


	public void setEat_cont2(String eat_cont2) {
		this.eat_cont2 = eat_cont2;
	}


	public String getEat_del() {
		return eat_del;
	}


	public void setEat_del(String eat_del) {
		this.eat_del = eat_del;
	}


	public String getCom_name() {
		return com_name;
	}


	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}


	public String getEat_sta() {
		return eat_sta;
	}


	public void setEat_sta(String eat_sta) {
		this.eat_sta = eat_sta;
	}

	
	
	
}
