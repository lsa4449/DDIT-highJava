package vo;

public class DealVO {

	private int dealNum; // 거래중개 번호
	private int memNum; // 회원 번호(FK)
	private int memNum2; // 회원 번호 (회원 - 회원 끼리 중개)
	private String account; // 가상계좌

	public int getDealNum() {
		return dealNum;
	}

	public void setDealNum(int dealNum) {
		this.dealNum = dealNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getMemNum2() {
		return memNum2;
	}

	public void setMemNum2(int memNum2) {
		this.memNum2 = memNum2;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "DealVO [dealNum=" + dealNum + ", memNum=" + memNum + ", memNum2=" + memNum2 + ", account=" + account
				+ "]";
	}

}
