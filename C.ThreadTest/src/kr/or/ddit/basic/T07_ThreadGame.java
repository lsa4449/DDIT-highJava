package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showinputDialog() 메서드를 이용하여 입력받습니다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행합니다.
 * 5초안에 입려깅 없으면 게임을 진 것으로 처리합니다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * ===결과===
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	boolean inputCheck = false;

	public static void main(String[] args) {
		Thread th1 = new CountTime();
		Thread th2 = new User();

		th1.start();
		th2.start();
	}
}

class User extends Thread {
	@Override
	public void run() {
		String user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요");
		T06_ThreadTest.inputCheck = true;
		String com = rand();

		if (com.equals(user)) {
			System.out.println("===결과===");
			System.out.println("컴퓨터 : " + com);
			System.out.println("당   신 : " + user);
			System.out.println("결   과 : 비겼습니다.");
		
		} else if (com.equals("가위") && user.equals("바위") || com.equals("바위") && user.equals("보")
				|| com.equals("보") && user.equals("가위")) {
			System.out.println("===결과===");
			System.out.println("컴퓨터 : " + com);
			System.out.println("당   신 : " + user);
			System.out.println("결   과 : 당신이 이겼습니다.");
		
		} else if (com.equals("가위") && user.equals("보") || com.equals("바위") && user.equals("가위")
				|| com.equals("보") && user.equals("바위")) {
			System.out.println("===결과===");
			System.out.println("컴퓨터 : " + com);
			System.out.println("당   신 : " + user);
			System.out.println("결   과 : 컴퓨터가 이겼습니다.");
		
		} else {
			System.out.println("잘 못 입력하셨습니다. 프로그램을 종료합니다.");
		}
	}

	public String rand() {
		int rand = (int) (Math.random() * 3) + 1;
		String com = "";
		if (rand == 1) {
			com = "가위";
		
		} else if (rand == 2) {
			com = "바위";
		
		} else {
			com = "보";
		}
		return com;
	}
}

class CountTime extends Thread {
	@Override
	public void run() {
		for (int i = 5; i > 0; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면 run() 메서드를 종료시킨다. 즉, 현재 쓰레드를 종료 시킨다.
			if (T06_ThreadTest.inputCheck) {
				return; // run()메서드가 종료되면 쓰레드도 끝난다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초 지나서 걍 끔");
		System.exit(0);
	}
}