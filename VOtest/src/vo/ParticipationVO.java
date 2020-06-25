package vo;

public class ParticipationVO {

	private int memNum; // 회원 번호
	private int chatNum; // 채팅방 번호

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getChatNum() {
		return chatNum;
	}

	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}

	@Override
	public String toString() {
		return "PaticipationVO [memNum=" + memNum + ", chatNum=" + chatNum + "]";
	}

}
