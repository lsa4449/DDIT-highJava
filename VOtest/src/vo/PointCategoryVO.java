package vo;

import java.util.Date;

public class PointCategoryVO {

	private int pointCategoryNum; // 포인트 내역 번호
	private int memNum; // 회원 번호
	private Date useDate; // 사용 날짜
	private String transPoint; // 거래 포인트
	private String resiPoint; // 잔여 포인트
	private int paymentNum; // 주문 번호

	public int getPointCategoryNum() {
		return pointCategoryNum;
	}

	public void setPointCategoryNum(int pointCategoryNum) {
		this.pointCategoryNum = pointCategoryNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getTransPoint() {
		return transPoint;
	}

	public void setTransPoint(String transPoint) {
		this.transPoint = transPoint;
	}

	public String getResiPoint() {
		return resiPoint;
	}

	public void setResiPoint(String resiPoint) {
		this.resiPoint = resiPoint;
	}

	public int getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}

	@Override
	public String toString() {
		return "PointCategoryVO [pointCategoryNum=" + pointCategoryNum + ", memNum=" + memNum + ", useDate=" + useDate
				+ ", transPoint=" + transPoint + ", resiPoint=" + resiPoint + ", paymentNum=" + paymentNum + "]";
	}

}
