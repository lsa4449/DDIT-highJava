package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 *  LPROD 테이블에 새로운 데이터를 추가하기
 *  
 *  lprod_gu와 lprod_nm은 직접 입력받아 처리하고,
 *  lprod_id는 현재의 lprod_id들 중 제일 큰 값보다 1 증가된 값으로 한다.
 *  (기타사항 : lprod_gu도 중복되는지 검사한다)
 * 
 */
public class T04_JdbcTest {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			String url = "jdbc:oracle:thin:@localhost:1521/xe";
//			String userId = "sua";
//			String password = "java";
//			conn = DriverManager.getConnection(url, userId, password);

			conn = DBUtil.getConnection();

			stmt = conn.createStatement();
			String sql = "select max(lprod_id) from lprod";

			rs = stmt.executeQuery(sql);

			int num = 0;
			if (rs.next()) {
				num = rs.getInt("max(lprod_id)");
			}
			num++;

			int count;
			String sql3 = "select count(*) cnt from lprod where lprod_gu =?";
			pstmt = conn.prepareStatement(sql3);
			String gu = null;

			do {
				System.out.print("lprod_gu 입력 >>");
				gu = s.next();
				pstmt.setString(1, gu);

				rs = pstmt.executeQuery();
				count = 0;
				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count > 0) {
					System.out.println(gu + "는 이미 존재하는 상품입니다.");
					System.out.println("다시 입력하세요!");
					System.out.println();
				}
			} while (count > 0);

			System.out.print("lprod_nm 입력>>");
			String nm = s.next();
			s.close();

			String sql2 = "insert into lprod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);

			pstmt.setInt(1, num);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(gu + "를 추가했습니다.");
			} else {
				System.out.println("실패!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
		
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}

	}
}