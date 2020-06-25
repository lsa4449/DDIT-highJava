package vo;

public class MySeatVO {

	private int mySeatNum; // 나의 좌석 번호
	private int overCount; // 연장 횟수
	private int reserCount; // 예약 횟수
	private int returnCount; // 반납 횟수
	private int seatRentalNum; // 대여 번호
	private int seatNum; // 좌석 관리 번호

	public int getMySeatNum() {
		return mySeatNum;
	}

	public void setMySeatNum(int mySeatNum) {
		this.mySeatNum = mySeatNum;
	}

	public int getOverCount() {
		return overCount;
	}

	public void setOverCount(int overCount) {
		this.overCount = overCount;
	}

	public int getReserCount() {
		return reserCount;
	}

	public void setReserCount(int reserCount) {
		this.reserCount = reserCount;
	}

	public int getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}

	public int getSeatRentalNum() {
		return seatRentalNum;
	}

	public void setSeatRentalNum(int seatRentalNum) {
		this.seatRentalNum = seatRentalNum;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public String toString() {
		return "MySeatVO [mySeatNum=" + mySeatNum + ", overCount=" + overCount + ", reserCount=" + reserCount
				+ ", returnCount=" + returnCount + ", seatRentalNum=" + seatRentalNum + ", seatNum=" + seatNum + "]";
	}

}
