package vo;

import java.util.Date;

public class PaymentVO {

	private int paymentNum; // 주문 번호
	private Date paymentDate; // 주문 일자
	private int memNum; // 회원 번호
	private int bookBasketNum; // 장바구니 번호
	private String allPay; // 총 금액

	public int getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBookBasketNum() {
		return bookBasketNum;
	}

	public void setBookBasketNum(int bookBasketNum) {
		this.bookBasketNum = bookBasketNum;
	}

	public String getAllPay() {
		return allPay;
	}

	public void setAllPay(String allPay) {
		this.allPay = allPay;
	}

	@Override
	public String toString() {
		return "PaymentVO [paymentNum=" + paymentNum + ", paymentDate=" + paymentDate + ", memNum=" + memNum
				+ ", bookBasketNum=" + bookBasketNum + ", allPay=" + allPay + "]";
	}

}
