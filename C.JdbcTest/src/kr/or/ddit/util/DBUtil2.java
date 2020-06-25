package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * db.properties파일의 내용으로 DB 정보를 설정하는 방법
 * 방법1)Properties객체 이용하기
 * 
 * 
 * 
 */

public class DBUtil2 {
	static Properties prop; //Properties객체변수 선언;
	
	static {
			prop = new Properties();
			File f = new File("res/db.properties");
			
	try {
		FileInputStream fin = new FileInputStream(f);
		prop.load(fin);
		
		Class.forName(prop.getProperty("driver"));
	} catch(IOException e) {
		e.printStackTrace();
	} catch(ClassNotFoundException e) {
		System.out.println("드라이빙로더 실패!");
		e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("user"),
											   prop.getProperty("pass"));
		}catch(SQLException e) {
			System.out.println("DB 연결 실패!");
			e.printStackTrace();
			return null;
		}

	}

}
