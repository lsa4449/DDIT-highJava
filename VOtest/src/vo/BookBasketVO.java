package vo;

public class BookBasketVO {

	private int bookBasketNum; // 장바구니 번호
	private int bookNum; // 도서 번호

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
		return "BookBasketVO [bookBasketNum=" + bookBasketNum + ", bookNum=" + bookNum + "]";
	}

}
