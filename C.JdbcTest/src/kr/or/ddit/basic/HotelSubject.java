package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelSubject {

	Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		new HotelSubject().start();
	}

	public void start() {

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = s.nextInt();

			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkout();
				break;
			case 3:
				room();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}

		}
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println(" 1. 체크인");
		System.out.println(" 2. 체크아웃");
		System.out.println(" 3. 객실상태");
		System.out.println(" 4. 업무종료");
		System.out.println("**************************");
		System.out.print(" 번호입력 >> ");
	}

	// 체크인
	public void checkIn() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "sua";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();
			
			System.out.print("방번호 입력 >>");
			int roomNum = Integer.parseInt(s.next());
			System.out.print("투숙객 입력 >>");
			String guestNm = s.next();

			String sql = "select * from hotel_mng where room_num = " + roomNum;
			rs = stmt.executeQuery(sql);

			if (rs.next() != false) {
				System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
				return;
				
			} else {
				String sql2 = "insert into hotel_mng values(" + roomNum + ",'" + guestNm + "')";

				pstmt = conn.prepareStatement(sql2);
				int input = pstmt.executeUpdate();

				System.out.println();
				System.out.println(input + "개의 예약이 완료 되었습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
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

	// 체크 아웃
	public void checkout() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "sua";
			String password = "java";

			System.out.print("체크 아웃할 방 번호 입력 >>");
			int roomNum = Integer.parseInt(s.next());

			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();

			String sql = "select * from hotel_mng where room_num = " + roomNum;
			rs = stmt.executeQuery(sql);

			if (rs.next() == false) {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
				return;

			} else {
				sql = " delete from hotel_mng where room_num = " + roomNum;
				System.out.println(roomNum + "번 체크아웃 되었습니다.");
				stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

	}

	// 객실 상태
	public void room() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "sua";
			String password = "java";

			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();

			String sql = "select * from hotel_mng";

			rs = stmt.executeQuery(sql);

			System.out.println("===실행 결과===");

			while (rs.next()) {
				System.out.println("방 번호 : " + rs.getInt("room_num"));
				System.out.println("투숙객 : " + rs.getString("guest_name"));
				System.out.println("--------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
