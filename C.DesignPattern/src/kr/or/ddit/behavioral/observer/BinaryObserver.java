package kr.or.ddit.behavioral.observer;

public class BinaryObserver extends Observer {
	
	public BinaryObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("바이너리 문자열 : " + Integer.toBinaryString(subject.getState()));
		
	}
}
