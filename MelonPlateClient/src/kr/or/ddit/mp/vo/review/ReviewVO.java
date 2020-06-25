package kr.or.ddit.mp.vo.review;

import java.io.Serializable;

public class ReviewVO implements Serializable {
	
	private String re_no;
	private String com_id;
	private String re_title;
	private String re_date;
	private String re_cont;
	private String re_photo;
	private String re_up;
	private String re_down;
	private String mem_no;
	private String re_score;
	private String rownum;
	
	//리뷰에서 사용하는 테이블 때문에 mem_nick 컬럼 추가
	private String mem_nick;
	
	//사용자 마이리뷰에서 사용할 테이블 - com_no 컬럼 추가
	private String com_name;
	
	//관리자 리뷰관리테이블 - mem_id 컬럼 추가★
	private String mem_id;
	
	//홀릭 달성 페이지
	private String count_review;
	private String holic;
	
	public ReviewVO(String re_no, String com_id, String re_title, String re_date, String re_cont, String re_photo,
			String re_up, String re_down, String mem_no, String re_score, String rownum, String mem_nick,
			String com_name, String mem_id, String count_review, String holic) {
		super();
		this.re_no = re_no;
		this.com_id = com_id;
		this.re_title = re_title;
		this.re_date = re_date;
		this.re_cont = re_cont;
		this.re_photo = re_photo;
		this.re_up = re_up;
		this.re_down = re_down;
		this.mem_no = mem_no;
		this.re_score = re_score;
		this.rownum = rownum;
		this.mem_nick = mem_nick;
		this.com_name = com_name;
		this.mem_id = mem_id;
		this.count_review = count_review;
		this.holic = holic;
	}
	public String getHolic() {
		return holic;
	}
	public void setHolic(String holic) {
		this.holic = holic;
	}
	public String getCount_review() {
		return count_review;
	}
	public void setCount_review(String count_review) {
		this.count_review = count_review;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getRe_score() {
		return re_score;
	}
	public void setRe_score(String re_score) {
		this.re_score = re_score;
	}
	public String getRe_no() {
		return re_no;
	}
	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	public String getRe_cont() {
		return re_cont;
	}
	public void setRe_cont(String re_cont) {
		this.re_cont = re_cont;
	}
	public String getRe_photo() {
		return re_photo;
	}
	public void setRe_photo(String re_photo) {
		this.re_photo = re_photo;
	}
	public String getRe_up() {
		return re_up;
	}
	public void setRe_up(String re_up) {
		this.re_up = re_up;
	}
	public String getRe_down() {
		return re_down;
	}
	public void setRe_down(String re_down) {
		this.re_down = re_down;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
	
	public ReviewVO() {
	
	}
	
	
}
