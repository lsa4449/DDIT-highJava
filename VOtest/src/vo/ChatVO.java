package vo;

import java.util.Date;

public class ChatVO {

	private int chatNum; // 채팅방 번호
	private Date establishDate; // 개설일자
	private int chatMem; // 참여 회원 수

	public int getChatNum() {
		return chatNum;
	}

	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}

	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	public int getChatMem() {
		return chatMem;
	}

	public void setChatMem(int chatMem) {
		this.chatMem = chatMem;
	}

	@Override
	public String toString() {
		return "ChatVO [chatNum=" + chatNum + ", establishDate=" + establishDate + ", chatMem=" + chatMem + "]";
	}

}
