package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil3 {
	static ResourceBundle bundle; // ResourceBundle 객체 변수 선언

	static {
		bundle = ResourceBundle.getBundle("db"); // 객체 생성
		try {
			Class.forName(bundle.getString("driver"));

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sua", "java");
		} catch (SQLException e) {
			System.out.println("DB연결 실패!");
			e.printStackTrace();
			return null;
		}
	}
}
