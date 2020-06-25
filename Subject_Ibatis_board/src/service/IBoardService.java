package service;

import java.util.List;

import vo.BoardVO;

public interface IBoardService {

	public List<BoardVO> getAllBoardList();
	
	public boolean getBoard(int boardNo);

	public int insertBoard(BoardVO bv);

	public int updateBoard(BoardVO bv);

	public int deleteBoard(int boardNo);
	
	public List<BoardVO> searchBoard(BoardVO bv);

}
