package vo;

public class SeatVO {

	private int seatNum; // 좌석 번호
	private boolean seatState; // 좌석 상태

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public boolean isSeatState() {
		return seatState;
	}

	public void setSeatState(boolean seatState) {
		this.seatState = seatState;
	}

	@Override
	public String toString() {
		return "SeatVO [seatNum=" + seatNum + ", seatState=" + seatState + "]";
	}

}
