package vo;

public class FilesVO {

	private int fileNum; // 파일 번호
	private String fileName; // 파일명
	private int boardNum; // 게시글 번호

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	@Override
	public String toString() {
		return "FilesVO [fileNum=" + fileNum + ", fileName=" + fileName + ", boardNum=" + boardNum + "]";
	}

}
