package kr.or.ddit.mp.vo.reservation;

import java.io.Serializable;

public class ReserOptionVO implements Serializable{

	private String com_id;
	private String op_person;
	private String op_time;
	private String cl_time;
	private String op_breakday;
	private String etc_option;
	
	public String getEtc_option() {
		return etc_option;
	}
	public void setEtc_option(String etc_option) {
		this.etc_option = etc_option;
	}
	public String getCl_time() {
		return cl_time;
	}
	public void setCl_time(String cl_time) {
		this.cl_time = cl_time;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getOp_person() {
		return op_person;
	}
	public void setOp_person(String op_person) {
		this.op_person = op_person;
	}
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getOp_breakday() {
		return op_breakday;
	}
	public void setOp_breakday(String op_breakday) {
		this.op_breakday = op_breakday;
	}
	
	public ReserOptionVO(String com_id, String op_person, String op_time, String cl_time, String op_breakday,
			String etc_option) {
		super();
		this.com_id = com_id;
		this.op_person = op_person;
		this.op_time = op_time;
		this.cl_time = cl_time;
		this.op_breakday = op_breakday;
		this.etc_option = etc_option;
	}
	public ReserOptionVO() {
	
	}
	
	
	
}
