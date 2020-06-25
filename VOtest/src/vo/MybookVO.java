package vo;

public class MybookVO {

	private int myBookNum; // 나의 도서 번호
	private int bookRentalNum; // 대여 번호
	private int memNum; // 회원 번호
	private int overCount; // 연체 횟수
	private int rentalCount; // 대출 횟수
	private int returnCount; // 반납 횟수
	private int reserCount; // 예약 횟수

	public int getMyBookNum() {
		return myBookNum;
	}

	public void setMyBookNum(int myBookNum) {
		this.myBookNum = myBookNum;
	}

	public int getBookRentalNum() {
		return bookRentalNum;
	}

	public void setBookRentalNum(int bookRentalNum) {
		this.bookRentalNum = bookRentalNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getOverCount() {
		return overCount;
	}

	public void setOverCount(int overCount) {
		this.overCount = overCount;
	}

	public int getRentalCount() {
		return rentalCount;
	}

	public void setRentalCount(int rentalCount) {
		this.rentalCount = rentalCount;
	}

	public int getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}

	public int getReserCount() {
		return reserCount;
	}

	public void setReserCount(int reserCount) {
		this.reserCount = reserCount;
	}

	@Override
	public String toString() {
		return "MybookVO [myBookNum=" + myBookNum + ", bookRentalNum=" + bookRentalNum + ", memNum=" + memNum
				+ ", overCount=" + overCount + ", rentalCount=" + rentalCount + ", returnCount=" + returnCount
				+ ", reserCount=" + reserCount + "]";
	}

}
