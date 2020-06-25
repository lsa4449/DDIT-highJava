package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_JdbcTest {

	/*
	 * 문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가
	 * 		큰 자료들을 출력하시오.
	 * 문제2) lprod_id값을 2개 입력 받아서 두 값중 작은 값부터 큰 값 사이의 자료를 출력하시오.
	 * 
	 */
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.print("lprod_id 입력 >>");
		int inputId = Integer.parseInt(s.next());
		
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
		
			String sql = "select * from lprod where lprod_id >" + inputId;
			
			rs = stmt.executeQuery(sql); 
			
			System.out.println("실행한 쿼리문 :" + sql);
			System.out.println("===쿼리문 실행 결과===");
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------------");
			}	
				
			System.out.println("문제 2");	
			System.out.println();
			
			System.out.print("lprod_id 첫번째 입력 >>");
			int inputId2 = Integer.parseInt(s.next());
			System.out.print("lprod_id 두번째 입력 >>");
			int inputId3 = Integer.parseInt(s.next());
			
			int min = 0;
			int max = 0;
			if (inputId2 > inputId3) {
				max = inputId2;
				min = inputId3;
			} else {
				max = inputId3;
				min = inputId2;
			}
			
			String sql2 = "select * from lprod where lprod_id between " + min + " and " + max;
			
			rs = stmt.executeQuery(sql2); 
			
			System.out.println("===쿼리문 실행 결과===");
			
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------------");
			}	
			
			System.out.println("자료 출력 끝!!");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
			
		}

		
	}	
}

