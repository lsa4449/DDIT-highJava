package vo;

public class WannaBookVO {

	private int wannaBookNum; // 희망 도서 번호
	private int bookNum; // 도서 번호
	private int memNum; // 회원 번호

	public int getWannaBookNum() {
		return wannaBookNum;
	}

	public void setWannaBookNum(int wannaBookNum) {
		this.wannaBookNum = wannaBookNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "WannaBookVO [wannaBookNum=" + wannaBookNum + ", bookNum=" + bookNum + ", memNum=" + memNum + "]";
	}

}
