package vo;

public class ReplyVO {

	private int replyNum; // 답글 번호
	private String replyContent; // 답글 내용
	private int rippleNum; // 댓글 번호
	private int memNum; // 회원 번호
	private int boardNum; // 게시글 번호

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRippleNum() {
		return rippleNum;
	}

	public void setRippleNum(int rippleNum) {
		this.rippleNum = rippleNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNum=" + replyNum + ", replyContent=" + replyContent + ", rippleNum=" + rippleNum
				+ ", memNum=" + memNum + ", boardNum=" + boardNum + "]";
	}

}
