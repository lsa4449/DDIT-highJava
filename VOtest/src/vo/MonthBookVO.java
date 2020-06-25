package vo;

public class MonthBookVO {

	private int monthBookNum; // 이달이 도서 번호
	private int bookNum; // 도서 번호
	private boolean memberRating; // 사용자 별점
	private boolean managerRating; // 관리자 별점
	private String memForRating; // 별점 부여 회원

	public int getMonthBookNum() {
		return monthBookNum;
	}

	public void setMonthBookNum(int monthBookNum) {
		this.monthBookNum = monthBookNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public boolean isMemberRating() {
		return memberRating;
	}

	public void setMemberRating(boolean memberRating) {
		this.memberRating = memberRating;
	}

	public boolean isManagerRating() {
		return managerRating;
	}

	public void setManagerRating(boolean managerRating) {
		this.managerRating = managerRating;
	}

	public String getMemForRating() {
		return memForRating;
	}

	public void setMemForRating(String memForRating) {
		this.memForRating = memForRating;
	}

	@Override
	public String toString() {
		return "MonthBookVO [monthBookNum=" + monthBookNum + ", bookNum=" + bookNum + ", memberRating=" + memberRating
				+ ", managerRating=" + managerRating + ", memForRating=" + memForRating + "]";
	}

}
