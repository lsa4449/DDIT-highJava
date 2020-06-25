package vo;

public class PaymentBasketVO {

	private int paymentBasketHistoryNum; // 주문 내역 번호
	private int paymentNum; // 주문 번호
	private int bookNum; // 도서 번호
	private String quantity; // 수량

	public int getPaymentBasketHistoryNum() {
		return paymentBasketHistoryNum;
	}

	public void setPaymentBasketHistoryNum(int paymentBasketHistoryNum) {
		this.paymentBasketHistoryNum = paymentBasketHistoryNum;
	}

	public int getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PaymentBasketVO [paymentBasketHistoryNum=" + paymentBasketHistoryNum + ", paymentNum=" + paymentNum
				+ ", bookNum=" + bookNum + ", quantity=" + quantity + "]";
	}

}
