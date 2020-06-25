package kr.or.ddit.behavioral.observer;

public class OctalObserver extends Observer {

	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("옥탈 문자열 : " + Integer.toOctalString(subject.getState()));
		
	}
}
