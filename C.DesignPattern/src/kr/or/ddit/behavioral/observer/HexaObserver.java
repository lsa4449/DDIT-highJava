package kr.or.ddit.behavioral.observer;

public class HexaObserver extends Observer {
	
	public HexaObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("핵사 문자열 : " + Integer.toHexString(subject.getState()));
		
	}
}
