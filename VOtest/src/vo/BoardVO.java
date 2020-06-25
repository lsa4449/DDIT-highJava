package vo;

import java.util.Date;

public class BoardVO {

	private int boardNum; // 게시글 번호
	private String boardTitle; // 게시글 명 
	private String boardContent; // 게시글 내용
	private Date boardDate; // 작성일자
	private int boardCount; // 조회수
	private int boardKindNum; // 게시판 종류 번호
	private int memNum; // 회원 번호

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getBoardKindNum() {
		return boardKindNum;
	}

	public void setBoardKindNum(int boardKindNum) {
		this.boardKindNum = boardKindNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", boardCount=" + boardCount + ", boardKindNum=" + boardKindNum
				+ ", memNum=" + memNum + "]";
	}

}
