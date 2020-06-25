package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 	5명의 사람이름을 입력하여 ArrayList에 저장하고 이 중에 '김'씨 성의 이름을 출력하시오.
 	(단, 입력은 스캐너를 이용하여 입력받는다.)
  
 */

public class T03_ArrayListTest {
	
	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
		
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			System.out.print("이름 쓰기 >>");			
			list.add(s.nextLine());
		}
		
		System.out.println("김씨 성을 가진 사람들");
			for(int i = 0; i < list.size(); i++) {
				String name = list.get(i);
			
				if(name.substring(0,1).equals("김"))
				System.out.println(list.get(i));
			}
		
	}

}
