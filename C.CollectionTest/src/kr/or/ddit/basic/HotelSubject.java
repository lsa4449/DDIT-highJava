package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelSubject {

	public static void main(String[] args) {
		new HotelSubject().start();
	}

	private Scanner s;
	private Map<Integer, Room> check;

	public HotelSubject() {
		s = new Scanner(System.in);
		check = new HashMap<>();

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

	public void start() {

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = Integer.parseInt(s.nextLine());

			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
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

	// 체크인
	private void checkIn() {

		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");

		System.out.print("체크인 할 번호 입력 >>");
		int num = Integer.parseInt(s.nextLine());

		System.out.println("누구를 체크인 하시겠습니까?");

		System.out.print("체크인 할 이름 입력 >>");
		String name = s.nextLine();

		if (check.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}

		check.put(num, new Room(num, name));
		System.out.println("체크인 되었습니다.");

	}

	// 체크 아웃
	private void checkOut() {

		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");

		System.out.print("체크아웃 할 번호 입력 >>");
		int num = Integer.parseInt(s.nextLine());

		if (check.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
			return;

		} else {
			System.out.println("체크아웃 되었습니다.");
		}
	}

	// 객실 상태
	private void room() {

		Set<Integer> keySet = check.keySet();

		if (keySet.size() == 0) {
			System.out.println("등록된 예약이 없습니다.");
		} else {
			Iterator<Integer> it = keySet.iterator();

			while (it.hasNext()) {
				int num = it.next();
				Room r = check.get(num);
				System.out.println("방번호 : " + r.getNum() + ", 투숙객 : " + r.getName());
			}

		}

		System.out.println("=======================================================");
		System.out.println("출력 완료!");
	}
}

class Room {

	private int num;
	private String name;

	public Room(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
