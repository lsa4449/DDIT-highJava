package vo;

public class BoardKindVO {

	private int boardKindNum; // 게시판종류 번호
	private String boardKindName; // 게시판종류 명

	public int getBoardKindNum() {
		return boardKindNum;
	}

	public void setBoardKindNum(int boardKindNum) {
		this.boardKindNum = boardKindNum;
	}

	public String getBoardKindName() {
		return boardKindName;
	}

	public void setBoardKindName(String boardKindName) {
		this.boardKindName = boardKindName;
	}

	@Override
	public String toString() {
		return "BoardKindVO [boardKindNum=" + boardKindNum + ", boardKindName=" + boardKindName + "]";
	}

}
