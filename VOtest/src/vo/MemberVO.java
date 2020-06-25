package vo;

import java.util.Date;

public class MemberVO {

	private int memNum; // 회원 번호
	private String id; // 아이디
	private String pw; // 비밀번호
	private String rrnNum; // 주민등록번호
	private String nickName; // 닉네임
	private String name; // 이름
	private String addr; // 주소
	private String addr2; // 상세주소
	private Date joinNow; // 가입일자
	private String tel; // 전화번호
	private String email; // 이메일 
	private boolean authority; // 권한

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRrnNum() {
		return rrnNum;
	}

	public void setRrnNum(String rrnNum) {
		this.rrnNum = rrnNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public Date getJoinNow() {
		return joinNow;
	}

	public void setJoinNow(Date joinNow) {
		this.joinNow = joinNow;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAuthority() {
		return authority;
	}

	public void setAuthority(boolean authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberVO [memNum=" + memNum + ", id=" + id + ", pw=" + pw + ", rrnNum=" + rrnNum + ", nickName="
				+ nickName + ", name=" + name + ", addr=" + addr + ", addr2=" + addr2 + ", joinNow=" + joinNow
				+ ", tel=" + tel + ", email=" + email + ", authority=" + authority + "]";
	}

}
