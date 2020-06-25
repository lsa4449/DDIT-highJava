package controller;

import java.util.List;
import java.util.Scanner;

import service.IMemberService;
import service.MemberServiceImpl;
import vo.MemberVO;


public class MemberMain {

	private IMemberService service = MemberServiceImpl.getInstance();
	private Scanner scan = new Scanner(System.in);

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. id 검색");
		System.out.println("  3. 전체 자료 출력");
		System.out.println("  4. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기

			switch (choice) {
			case 1: 
				insertMember();
				break;
			case 2: // 자료 삭제
				searchMember();
				break;
			case 3: // 자료 수정
				allMemberList();
				break;
			case 4: // 전체 자료 출력
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 4);
	}

	private void allMemberList() {
		
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------");

		List<MemberVO> memList = service.getMemberAll();

		for (MemberVO mv : memList) {
			System.out.println(mv.getMem_id() + "\t" + mv.getMem_name() + "\t" + mv.getMem_tel() + "\t" + mv.getMem_addr());
		}

			System.out.println("-------------------------------------------");
			System.out.println("출력 작업 끝@@");

	}
	
	private void searchMember() {
		
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("회원 ID >>");
		String memId = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------");
		
		List<MemberVO> memList = service.getSearchMember(mv);

		for (MemberVO mv2 : memList) {
			System.out.println(mv2.getMem_id() + "\t" + mv2.getMem_name() + 
								"\t" + mv2.getMem_tel() + "\t" + mv2.getMem_addr());
		}
			System.out.println("-------------------------------------------");
	}
		
	private void insertMember() {
		
		System.out.print("회원 아이디>>");
		String memId = scan.next();
		
		System.out.print("회원 이름 >>");
		String memName = scan.next();

		System.out.print("회원 전화번호 >>");
		String memTel = scan.next();

		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("회원 주소 >>");
		String memAddr = scan.nextLine();

		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);

		service.insertMember(mv);

	}

	public static void main(String[] args) {
		MemberMain m = new MemberMain();
		m.start();
	}
		
}

