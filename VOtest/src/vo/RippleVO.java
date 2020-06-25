package vo;

public class RippleVO {

	private int rippleNum; // 댓글 번호
	private String rippleContent; // 댓글 내용
	private int boardNum; // 게시글 번호
	private int memNum; // 회원 번호

	public int getRippleNum() {
		return rippleNum;
	}

	public void setRippleNum(int rippleNum) {
		this.rippleNum = rippleNum;
	}

	public String getRippleContent() {
		return rippleContent;
	}

	public void setRippleContent(String rippleContent) {
		this.rippleContent = rippleContent;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "RippleVO [rippleNum=" + rippleNum + ", rippleContent=" + rippleContent + ", boardNum=" + boardNum
				+ ", memNum=" + memNum + "]";
	}

}
