package kr.or.ddit.mp.vo.mypageEatDealQnA;

import java.io.Serializable;

public class EatqaVO implements Serializable {
	private String eatqa_no;
	private String eat_no;
	private String eatqa_title;
	private String in_date;
	private String eatqa_cont;
	private String eatqa_com;
	private String eatqa_com_st;
	private String mem_no;
	// 추가
	private String up_date;
	private String eat_name;
	private String eatqa_str_st;
	private String rownum;

	
	public EatqaVO(String eatqa_no, String eat_no, String eatqa_title, String in_date, String eatqa_cont,
			String eatqa_com, String eatqa_com_st, String mem_no, String up_date, String eat_name, String eatqa_str_st,
			String rownum) {
		super();
		this.eatqa_no = eatqa_no;
		this.eat_no = eat_no;
		this.eatqa_title = eatqa_title;
		this.in_date = in_date;
		this.eatqa_cont = eatqa_cont;
		this.eatqa_com = eatqa_com;
		this.eatqa_com_st = eatqa_com_st;
		this.mem_no = mem_no;
		this.up_date = up_date;
		this.eat_name = eat_name;
		this.eatqa_str_st = eatqa_str_st;
		this.rownum = rownum;
	}

	public EatqaVO() {
		// TODO Auto-generated constructor stub
	}

	public String getEatqa_str_st() {
		return eatqa_str_st;
	}

	public void setEatqa_str_st(String eatqa_str_st) {
		this.eatqa_str_st = eatqa_str_st;
	}

	public String getEat_name() {
		return eat_name;
	}

	public void setEat_name(String eat_name) {
		this.eat_name = eat_name;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	public String getEatqa_no() {
		return eatqa_no;
	}

	public void setEatqa_no(String eatqa_no) {
		this.eatqa_no = eatqa_no;
	}

	public String getEat_no() {
		return eat_no;
	}

	public void setEat_no(String eat_no) {
		this.eat_no = eat_no;
	}

	public String getEatqa_title() {
		return eatqa_title;
	}

	public void setEatqa_title(String eatqa_title) {
		this.eatqa_title = eatqa_title;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getEatqa_cont() {
		return eatqa_cont;
	}

	public void setEatqa_cont(String eatqa_cont) {
		this.eatqa_cont = eatqa_cont;
	}

	public String getEatqa_com() {
		return eatqa_com;
	}

	public void setEatqa_com(String eatqa_com) {
		this.eatqa_com = eatqa_com;
	}

	public String getEatqa_com_st() {
		return eatqa_com_st;
	}

	public void setEatqa_com_st(String eatqa_com_st) {
		this.eatqa_com_st = eatqa_com_st;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

}
