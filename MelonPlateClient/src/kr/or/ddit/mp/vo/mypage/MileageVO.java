package kr.or.ddit.mp.vo.mypage;

import java.io.Serializable;

public class MileageVO implements Serializable{
	private String mel_no;
	private String mem_no;
	private String order_no;
	private String melonage;
	private String mel_use;
	private String mel_add;
	private String mel_update;
	private String mel_indate;
	public MileageVO() {
		// TODO Auto-generated constructor stub
	}
	public MileageVO(String mel_no, String mem_no, String order_no, String melonage, String mel_use, String mel_add,
			String mel_update, String mel_indate) {
		super();
		this.mel_no = mel_no;
		this.mem_no = mem_no;
		this.order_no = order_no;
		this.melonage = melonage;
		this.mel_use = mel_use;
		this.mel_add = mel_add;
		this.mel_update = mel_update;
		this.mel_indate = mel_indate;
	}
	
	public String getMel_no() {
		return mel_no;
	}
	public void setMel_no(String mel_no) {
		this.mel_no = mel_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMelonage() {
		return melonage;
	}
	public void setMelonage(String melonage) {
		this.melonage = melonage;
	}
	public String getMel_use() {
		return mel_use;
	}
	public void setMel_use(String mel_use) {
		this.mel_use = mel_use;
	}
	public String getMel_add() {
		return mel_add;
	}
	public void setMel_add(String mel_add) {
		this.mel_add = mel_add;
	}
	public String getMel_update() {
		return mel_update;
	}
	public void setMel_update(String mel_update) {
		this.mel_update = mel_update;
	}
	public String getMel_indate() {
		return mel_indate;
	}
	public void setMel_indate(String mel_indate) {
		this.mel_indate = mel_indate;
	}
}
