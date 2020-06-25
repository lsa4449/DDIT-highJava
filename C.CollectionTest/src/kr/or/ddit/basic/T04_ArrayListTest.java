package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 	1 ) 5명의 별명을 입력하여 ArrayList에 저장하고 별명 길이가 제일 긴 별명을 출력하시오.
 		(단, 각 별명의 길이는 모두 다르게 입력한다.)
 	2 ) 문제 1에서 별명의 길이가 같은 것을 여러개 입력했을 때에도 처리 되도록 하시오	
  
 */

public class T04_ArrayListTest {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			System.out.print("별명 입력 >>");
			list.add(s.nextLine());
		}

		int max1 = 0;

		for (int i = 0; i < list.size(); i++) {
			if (max1 < list.get(i).length()) {
				max1 = list.get(i).length();
			}
		}

		System.out.println("제일 긴 별명"); 
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() == max1) {
				System.out.println(list.get(i));
			}
		}

	}
}