package vo;

public class BookBasketHistory {

	private int bookBasketHistoryNum; // 장바구니 내역 번호
	private int memNum; // 회원 번호
	private int bookBasketNum; // 장바구니 번호
	private int bookNum; // 도서 번호

	public int getBookBasketHistoryNum() {
		return bookBasketHistoryNum;
	}

	public void setBookBasketHistoryNum(int bookBasketHistoryNum) {
		this.bookBasketHistoryNum = bookBasketHistoryNum;
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

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	@Override
	public String toString() {
		return "BookBasketHistory [bookBasketHistoryNum=" + bookBasketHistoryNum + ", memNum=" + memNum
				+ ", bookBasketNum=" + bookBasketNum + ", bookNum=" + bookNum + "]";
	}

}
