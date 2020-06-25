package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberMain {

	private IMemberService service = MemberServiceImpl.getInstance();
	Scanner scan = new Scanner(System.in);

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
			displayMenu();
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				selectById();
				break;
			case 3:
				selectAll();
				break;
			case 4: // 전체 자료 출력
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 4);
	}

	private void selectAll() {

		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------");

		List<MemberVO> list = service.selectAll();

		for (MemberVO mv : list) {
			System.out.println(
					mv.getMem_id() + "\t" + mv.getMem_name() + "\t" + mv.getMem_tel() + "\t" + mv.getMem_addr());

		}
	}

	private void selectById() {

		String id = null;

		selectAll();
		System.out.println("검색할 회원 id를 입력하세요.");
		System.out.print("회원 id >> ");
		id = scan.next();
		
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------");


		MemberVO mv = service.selectById(id);

		System.out.println(
				mv.getMem_id() + "\t" + mv.getMem_name() + "\t" + mv.getMem_tel() + "\t" + mv.getMem_addr());

	}

	private void insert() {

		System.out.print("회원 ID >>");
		String id = scan.next();

		System.out.print("회원 이름 >>");
		String name = scan.next();

		System.out.print("회원 번호 >>");
		String tel = scan.next();

		System.out.print("회원 주소 >>");
		String addr = scan.next();

		MemberVO mv = new MemberVO();
		mv.setMem_id(id);
		mv.setMem_name(name);
		mv.setMem_tel(tel);
		mv.setMem_addr(addr);

		service.insertMember(mv);
	}

	public static void main(String[] args) {
		MemberMain m = new MemberMain();

		m.start();
	}
}
