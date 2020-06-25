package vo;

public class RecentlyBookVO {

	private int recentlyBookNum; // 최근 본 도서
	private int memNum; // 회원 번호
	private int bookNum; // 도서 번호

	public int getRecentlyBookNum() {
		return recentlyBookNum;
	}

	public void setRecentlyBookNum(int recentlyBookNum) {
		this.recentlyBookNum = recentlyBookNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	@Override
	public String toString() {
		return "RecentlyBookVO [recentlyBookNum=" + recentlyBookNum + ", memNum=" + memNum + ", bookNum=" + bookNum
				+ "]";
	}

}
