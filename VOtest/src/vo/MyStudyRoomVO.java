package vo;

public class MyStudyRoomVO {

	private int myStudyRoomNum; // 나의 스터디룸 번호
	private int overCount; // 연장 횟수
	private int reserCount; // 예약 횟수
	private int returnCount; // 반납 횟수
	private int studyRoomRentalNum; // 대여 번호
	private int studyRoomNum; // 스터디룸 관리 번호

	public int getMyStudyRoomNum() {
		return myStudyRoomNum;
	}

	public void setMyStudyRoomNum(int myStudyRoomNum) {
		this.myStudyRoomNum = myStudyRoomNum;
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

	public int getStudyRoomRentalNum() {
		return studyRoomRentalNum;
	}

	public void setStudyRoomRentalNum(int studyRoomRentalNum) {
		this.studyRoomRentalNum = studyRoomRentalNum;
	}

	public int getStudyRoomNum() {
		return studyRoomNum;
	}

	public void setStudyRoomNum(int studyRoomNum) {
		this.studyRoomNum = studyRoomNum;
	}

	@Override
	public String toString() {
		return "MyStudyRoomVO [myStudyRoomNum=" + myStudyRoomNum + ", overCount=" + overCount + ", reserCount="
				+ reserCount + ", returnCount=" + returnCount + ", studyRoomRentalNum=" + studyRoomRentalNum
				+ ", studyRoomNum=" + studyRoomNum + "]";
	}

}
