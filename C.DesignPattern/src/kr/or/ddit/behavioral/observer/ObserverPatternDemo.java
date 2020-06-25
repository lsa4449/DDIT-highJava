package kr.or.ddit.behavioral.observer;

public class ObserverPatternDemo {
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		new HexaObserver(subject);
		new OctalObserver(subject);
		new BinaryObserver(subject);
		
		System.out.println("처음 상태 변경 : 15");
		subject.setState(15);
		System.out.println("두번째 상태 변경하기 : 10");
		subject.setState(10);
		System.out.println("수아야 너자리 완전 직빵이구나...ㅎ....ㅎ 너자리좋당 ㅎ_ㅎ !");
		
	}
}
