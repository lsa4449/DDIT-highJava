package dao;

import java.util.List;

import vo.BoardVO;

public interface IBoardDao {
	
	public List<BoardVO> getAllBoardList();
	
	public boolean getBoard(int boardNo);
	
	public int insertBoard(BoardVO bv);
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(int boardNo);
	
	public BoardVO searchBoard(int boardNo);

}
