package dao;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.BoardVO;


public class BoardDaoImpl implements IBoardDao {

	private SqlMapClient smc;
	private static IBoardDao dao;

	private BoardDaoImpl() {
		
		Reader rd;
		try {
		 rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		 smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		 rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!~@");
			e.printStackTrace();
		}
	}

	public static IBoardDao getInstance() {
		if (dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {

		List<BoardVO> bList = new ArrayList<BoardVO>();
	
		try {
			bList = smc.queryForList("board.getBoardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;
		
		try {
			Object obj = smc.insert("board.insertBoard", bv);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
		
	}

	@Override
	public int updateBoard(BoardVO bv) {

		int cnt = 0;

		try {
			cnt = smc.update("board.updateBoard", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {

		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getBoard(int boardNo) {
	
		boolean chk = false;
		
		try {
			BoardVO bv = (BoardVO) smc.queryForObject("board.getBoard", boardNo);

			if (bv != null) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {

		List<BoardVO> bList = new ArrayList<>();

		try {
			bList = smc.queryForList("board.getSearchBoard", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bList;
	}

}
