package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerMain {

	private IBuyerService service = BuyerServiceImpl.getInstance();
	Scanner scan = new Scanner(System.in);

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 조회하기");
		System.out.println("  2. id 검색");
		System.out.println("  3. 정보저장");
		System.out.println("  4. 작업 끝");
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
				search();
				break;
			case 2:
				selectById();
				break;
			case 3:
				insert();
				break;
			case 4: // 전체 자료 출력
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 4);
	}

	private void search() {
		
		System.out.println("검색할 정보를 입력하세요");
		System.out.print("ID 입력 >>");
		String id = scan.next();

		System.out.print("이름 입력 >>");
		String name = scan.next();

		BuyerVO bv = new BuyerVO();
		bv.setBuyer_id(id);
		bv.setBuyer_name(name);

		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("ID\t이름\t상품코드\t이메일\t우편번호\t도로명 주소\t상세주소\t집전화\t팩스");
		System.out.println("--------------------------------------------------------------");

		
		List<BuyerVO> list = service.search(bv);
		
		for(BuyerVO bv2 : list) {
			System.out.println(bv.getBuyer_id() + "\t" + bv.getBuyer_name() + "\t" + bv.getBuyer_lgu() + "\t"
					+ bv.getBuyer_mail() + "\t" + bv.getBuyer_zip() + "\t" + bv.getBuyer_add1() + "\t"
					+ bv.getBuyer_add2() + " \t" + bv.getBuyer_comtel() + "\t" + bv.getBuyer_fax());
		}
		
	}

	private void insert() {

		int cnt = 0;
		
		System.out.print("ID 입력 >>");
		String id = scan.next();

		System.out.print("이름 입력 >>");
		String name = scan.next();

		System.out.print("상품코드(예 :p101) 입력 >>");
		String lgu = scan.next();

		System.out.print("이메일 입력 >>");
		String mail = scan.next();

		System.out.print("우편번호(예:123-456) 입력 >>");
		String zip = scan.next();

		System.out.print("도로명 주소 입력 >>");
		String add1 = scan.next();

		System.out.print("상세주소 입력 >>");
		String add2 = scan.next();
		
		System.out.print("집전화 입력 >>");
		String comtel = scan.next();
		
		System.out.print("팩스번호 입력 >>");
		String fax = scan.next();

		BuyerVO bv = new BuyerVO();

		bv.setBuyer_id(id);
		bv.setBuyer_name(name);
		bv.setBuyer_lgu(lgu);
		bv.setBuyer_mail(mail);
		bv.setBuyer_zip(zip);
		bv.setBuyer_add1(add1);
		bv.setBuyer_add2(add2);
		bv.setBuyer_comtel(comtel);
		bv.setBuyer_fax(fax);
		
		cnt = service.insert(bv);

		if (cnt > 0) {
			System.out.println("정보 저장 성공");
		} else {
			System.out.println("정보 저장 실패");
		}
	}

	private void selectById() {
		
		String id = null;

		System.out.println("검색할 회원 id를 입력하세요.");
		System.out.print("회원 id >> ");
		id = scan.next();
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println("ID\t이름\t상품코드\t이메일\t우편번호\t도로명 주소\t상세주소\t집전화\t팩스");
		System.out.println("-------------------------------------------------------------------");


		BuyerVO bv = service.selectById(id);

		System.out.println(bv.getBuyer_id() + "\t" + bv.getBuyer_name() + "\t" + bv.getBuyer_lgu() + "\t"
						+ bv.getBuyer_mail() + "\t" + bv.getBuyer_zip() + "\t" + bv.getBuyer_add1() + "\t"
						+ bv.getBuyer_add2() + " \t" + bv.getBuyer_comtel() + "\t" + bv.getBuyer_fax());
				

	}

	public static void main(String[] args) {
		
		BuyerMain b = new BuyerMain();
		
		b.start();

	}

}
