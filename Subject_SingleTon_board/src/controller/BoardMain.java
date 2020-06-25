package controller;

import java.util.List;
import java.util.Scanner;

import service.BoardServiceImpl;
import service.IBoardService;
import vo.BoardVO;

public class BoardMain {

	private IBoardService service =BoardServiceImpl.getInstance();
	private Scanner scan = new Scanner(System.in);

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 등록");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 검색");
		System.out.println("  5. 전체 게시글 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기

			switch (choice) {
			case 1: // 자료 입력
				insertBoard();
				break;
			case 2: // 자료 삭제
				deleteBoard();
				break;
			case 3: // 자료 수정
				updateBoard();
				break;
			case 4: // 자료 검색
				searchBoard();
				break;
			case 5: // 전체 자료 출력
				displayBoardAll();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void insertBoard() {

		System.out.println("등록할 게시글을 입력하세요.");
		System.out.print("게시글 제목 >>");
		String boardTitle = scan.next();

		System.out.print("저자 >>");
		String boardWriter = scan.next();

		System.out.print("내용 >>");
		String boardContent = scan.next();

		BoardVO bv = new BoardVO();
		bv.setBoard_title(boardTitle);
		bv.setBoard_writer(boardWriter);
		bv.setBoard_content(boardContent);

		int cnt = service.insertBoard(bv);

		if (cnt > 0) {
			System.out.println("'" + boardTitle + "'" + " 글 등록 완료!!");
		} else {
			System.out.println("'" + boardTitle + "'" + " 글 등록 실패!!");
		}
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요 >>");
		int boardNo = Integer.parseInt(scan.next());

		int cnt = service.deleteBoard(boardNo);

		if (cnt > 0) {
			System.out.println(boardNo + "번째 글 삭제 성공");
		} else {
			System.out.println(boardNo + "번째 글 삭제 실패!@@#");
		}
	}

	private void updateBoard() {
		
		boolean chk = false; // 중복여부 체크
		int boardNo;

		do {
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >>");
			boardNo = Integer.parseInt(scan.next());

			chk = service.getBoard(boardNo);
			
			if (!chk) {
				System.out.println("게시글이 " + boardNo + "인  게시글은 존재하지 않습니다!");
				System.out.println("다시 입력하세요@!!");
			}

		} while (!chk);

		System.out.print("제목 >>");
		String boardTitle = scan.next();

		System.out.print("저자 >>");
		String boardWriter = scan.next();

		System.out.print("내용 >>");
		String boardContent = scan.next();

		BoardVO bv = new BoardVO();
		bv.setBoard_title(boardTitle);
		bv.setBoard_writer(boardWriter);
		bv.setBoard_content(boardContent);
		bv.setBoard_no(boardNo);

		int cnt = service.updateBoard(bv);

		if (cnt > 0) {
			System.out.println("'" + boardTitle + "'" + " 글 수정 완료!!");
		} else {
			System.out.println("'" + boardTitle + "'" + " 글 수정 실패!!");
		}
	}
	
	private void searchBoard() {
	      
	      boolean chk = false; //  중복여부 체크
	      int board_no;
	      
	      do {
	    	 displayBoardAll(); //전체목록출력
	         System.out.println("검색할 게시판 번호를 입력하세요.");
	         System.out.print("게시판 번호 >> ");
	         board_no = Integer.parseInt(scan.next());
	         chk = service.getBoard(board_no);
	         if(!chk) {
	            System.out.println("게시판 번호가 " + board_no 
	                                   + "인 회원은 존재하지 않습니다.");
	            System.out.println("다시 입력하세요.");
	         }
	      }while(!chk);

	      BoardVO bv = service.searchBoard(board_no);
	      System.out.println("------------------------전체리스트보기---------------------------");
	      System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() +"\t" + bv.getBoard_writer() +"\t" 
	    		  			+ bv.getBoard_date() +"\t" + bv.getBoard_content());
	      
	      
	   }

	private void displayBoardAll() {

		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("번호\t제목\t저자\t날짜\t\t내용");
		System.out.println("------------------------------------------------------");
	
		List<BoardVO> bList = service.getAllBoardList();
		
		for(BoardVO bv : bList) {
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t"
								+bv.getBoard_writer() + "\t" + bv.getBoard_date() + "\t" + bv.getBoard_content());	
		}
		
		System.out.println("-------------------------------------------");
		
	}

	public static void main(String[] args) {
		BoardMain bm = new BoardMain();
		bm.start();
	}

}