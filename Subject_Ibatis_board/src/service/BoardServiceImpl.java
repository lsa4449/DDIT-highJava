package service;

import java.util.List;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVO;

public class BoardServiceImpl implements IBoardService{

	private IBoardDao bDao;
	
	private static IBoardService service;

	private BoardServiceImpl() {
		bDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		return bDao.getAllBoardList();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		return bDao.insertBoard(bv);
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return bDao.updateBoard(bv);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return bDao.deleteBoard(boardNo);
	}

	@Override
	public boolean getBoard(int boardNo) {
		return bDao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		return bDao.searchBoard(bv);
	}

	

}
