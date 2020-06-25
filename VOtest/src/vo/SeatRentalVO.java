package vo;

import java.util.Date;

public class SeatRentalVO {

	private int memNum; // 회원 번호
	private int seatNum; // 좌석 관리 번호
	private int seatRentalNum; // 대여 번호
	private Date seatRentalTime; // 대여 시간
	private Date seatReturnTime; // 반납 시간

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getSeatRentalNum() {
		return seatRentalNum;
	}

	public void setSeatRentalNum(int seatRentalNum) {
		this.seatRentalNum = seatRentalNum;
	}

	public Date getSeatRentalTime() {
		return seatRentalTime;
	}

	public void setSeatRentalTime(Date seatRentalTime) {
		this.seatRentalTime = seatRentalTime;
	}

	public Date getSeatReturnTime() {
		return seatReturnTime;
	}

	public void setSeatReturnTime(Date seatReturnTime) {
		this.seatReturnTime = seatReturnTime;
	}

	@Override
	public String toString() {
		return "SeatRentalVO [memNum=" + memNum + ", seatNum=" + seatNum + ", seatRentalNum=" + seatRentalNum
				+ ", seatRentalTime=" + seatRentalTime + ", seatReturnTime=" + seatReturnTime + "]";
	}

}
