package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.BoardVO;


public class BoardDaoImpl implements IBoardDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IBoardDao dao;

	private BoardDaoImpl() {

	}

	public static IBoardDao getInstance() {
		if (dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	private void disConnect() {
		// 사용했던 자원 반납
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ee) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ee) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ee) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ee) {
			}
	}

	@Override
	public List<BoardVO> getAllBoardList() {

		List<BoardVO> bList = new ArrayList<BoardVO>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardVO bv = new BoardVO();

				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getDate("board_date"));
				bv.setBoard_content(rs.getString("board_content"));

				bList.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return bList;
	}

	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			String sql = "insert into jdbc_board values(board_seq.nextval,?,?,SYSDATE,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {

		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_writer = ?, board_content = ? where board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			pstmt.setInt(4, bv.getBoard_no());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {

		int cnt = 0;
		try {
			conn = DBUtil.getConnection();

			String sql = "delete from jdbc_board where board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public boolean getBoard(int boardNo) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();

		}
		return chk;
	}
	
	   @Override
	   public BoardVO searchBoard(int board_no) {
	     
		   BoardVO bv = new BoardVO();
	      try {
	         conn = DBUtil.getConnection();
	         String sql = "select * from jdbc_board where board_no=" + board_no;
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()) {
	            bv.setBoard_no(rs.getInt("board_no"));
	            bv.setBoard_title(rs.getString("board_title"));
	            bv.setBoard_writer(rs.getString("board_writer"));
	            bv.setBoard_date(rs.getDate("board_date"));
	            bv.setBoard_content(rs.getString("board_content"));
	         }
	         
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         disConnect();
	      }
	      return bv;
	   }


}
