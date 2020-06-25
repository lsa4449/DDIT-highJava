package vo;

public class StudyRoomVO {

	private int studyRoomNum; // 스터디룸 번호
	private boolean studyRoomState; // 스터디룸 상태
	private boolean studyRoomReserState; // 예약 상태

	public int getStudyRoomNum() {
		return studyRoomNum;
	}

	public void setStudyRoomNum(int studyRoomNum) {
		this.studyRoomNum = studyRoomNum;
	}

	public boolean isStudyRoomState() {
		return studyRoomState;
	}

	public void setStudyRoomState(boolean studyRoomState) {
		this.studyRoomState = studyRoomState;
	}

	public boolean isStudyRoomReserState() {
		return studyRoomReserState;
	}

	public void setStudyRoomReserState(boolean studyRoomReserState) {
		this.studyRoomReserState = studyRoomReserState;
	}

	@Override
	public String toString() {
		return "StudyRoomVO [studyRoomNum=" + studyRoomNum + ", studyRoomState=" + studyRoomState
				+ ", studyRoomReserState=" + studyRoomReserState + "]";
	}

}
