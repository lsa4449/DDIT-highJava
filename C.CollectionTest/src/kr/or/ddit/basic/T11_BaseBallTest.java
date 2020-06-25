package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제 ) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용하여 구한다.
 * 		(스트라이크는 'S', 볼은 'B'로 출력한다.)
 * 
 * 		컴퓨터의 난수가 9 5 7 일때 실행 예시)
 * 			숫자 입력 => 3 5 6
 * 			3 5 6 ===> 1S 0B
 * 			숫자입력 => 7 8 9
 * 			7 8 9 ===> 0S 2B
 * 
 * 			...
 * 
 * 			숫자 입력 => 9 5 7
 * 			9 5 7 ===> 3S 0B
 * 
 * 			5번째 안에 맞췄군요.
 * 
 */
public class T11_BaseBallTest {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		HashSet<Integer> intRnd = new HashSet<>();

		while (intRnd.size() < 3) {
			int num = (int) (Math.random() * (9) + 1);
			intRnd.add(num);
		}

		System.out.println("컴퓨터가 만든 난수 : " + intRnd);

		boolean game = true;
		int count = 0;

		System.out.println();
		System.out.println("야구 게임 시작 !!!");

		while (game) {
			int strike = 0;
			int ball = 0;
			int user[] = new int[3];
			int temp = 0;
			count++;

			System.out.println("숫자 입력 >>");
			for (int i = 0; i < user.length; i++) {
				temp = s.nextInt();
				user[i] = temp;
			}

			for (int i = 0; i < intRnd.size(); i++) {
				for (int j = 0; j < user.length; j++) {
					if (intRnd.size() == user[j] && i == j) {
						strike++;

					} else if (intRnd.size() == user[j] && i != j) {
						ball++;
					}

				}
			}
		System.out.println(strike + " S " + ball + " B ");	
			if(strike == 3) {
				System.out.println(count + "번만에 맞췄습니다.");
				game = false;
			}
		}

		
		
		System.out.println("=============================================");
		
		 Set com = new HashSet();   
	      int count1 = 0;

	      while (true) {
	         count1++;
	         int str = 0;
	         int ball = 0;
	         while (com.size() < 3) {            
	            com.add((int)(Math.random() * 9)+1);            
	         }
	         
	         List comcom = new ArrayList<>(com);
	        
	         Scanner sc = new Scanner(System.in);
	         
	         System.out.println(com);
	         
	         System.out.print("숫자 입력 => ");
	         int a = Integer.parseInt(sc.next());
	         int b = Integer.parseInt(sc.next());
	         int c = Integer.parseInt(sc.next());
	         
	         if((int)comcom.get(0)==a) {
	            str++;
	         }
	         if((int)comcom.get(1)==b) {
	            str++;
	         }
	         if((int)comcom.get(2)==c) {
	            str++;
	         }
	         if((int)comcom.get(0)==b || (int)comcom.get(0)==c) {
	            ball++;
	         }
	         if((int)comcom.get(1)==a || (int)comcom.get(1)==c) {
	            ball++;
	         }
	         if((int)comcom.get(2)==a || (int)comcom.get(2)==b) {
	            ball++;
	         }         
	         if(str==3) {
	            System.out.println(count1 + "번 만에 맞췄군요.");
	            break;
	         }
	         System.out.println(a + " " + b + " " + c + " => " + str + "S " + ball + "B");
	      }      

		
		
	}
}
