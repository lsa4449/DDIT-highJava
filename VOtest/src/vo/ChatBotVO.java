package vo;

import java.util.Date;

public class ChatBotVO {

	private int chatBotNum; // 챗봇 번호
	private String content; // 챗봇 내용
	private Date writeTime; // 글 쓴 시간
	private int memNum; // 회원 번호

	public int getChatBotNum() {
		return chatBotNum;
	}

	public void setChatBotNum(int chatBotNum) {
		this.chatBotNum = chatBotNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "ChatBotVO [chatBotNum=" + chatBotNum + ", content=" + content + ", writeTime=" + writeTime + ", memNum="
				+ memNum + "]";
	}

}
