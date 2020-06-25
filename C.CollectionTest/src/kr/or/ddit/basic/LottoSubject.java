package kr.or.ddit.basic;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LottoSubject {

	public static void main(String[] args) {

		int money = 0;

		Scanner sc = new Scanner(System.in);

		while (money < 10000) {
			System.out.println("=====================\nLotto 프로그램\n---------------------");
			System.out.println("1. Lotto 구입\n2. 프로그램 종료");
			System.out.println("=====================");
			System.out.print("메뉴선택 : ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Lotto 구입 시작\r\n" + "(1000원에 로또번호 하나입니다.)");

				System.out.println();
				System.out.print("금액을 입력 하세요 >>");
				money = sc.nextInt();
				
				if (money > 1000) {
					for (int i = 0; i < money / 1000; i++) {
						Set<Integer> intRnd = new TreeSet<>();

						while (intRnd.size() < 6) {
							int num = (int) (Math.random() * (45) + 1); // 1~100사이의 난수 만들기
							intRnd.add(num);

						}
						System.out.println("행운의 로또번호는 아래와 같습니다 >>" + intRnd);
					}
					System.out.println();
					System.out.println("받은 금액은 " + money + "이고 거스름돈은 " + (money % 1000) + "입니다.");

				} else if (money < 1000 && money < 0) {
					System.out.println("1000원 이상 돈을 넣어주세요!");
						return;
				}
				

			default :
				System.out.println("\n감사합니다.");
				System.exit(0);
			}
		}

	}

}
