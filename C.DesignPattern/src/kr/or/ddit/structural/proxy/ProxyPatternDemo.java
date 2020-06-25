package kr.or.ddit.structural.proxy;

public class ProxyPatternDemo {

	public static void main(String[] args) {
		Image image = new ProxyImage("테스트 파일_100MB짜리.jpg");

		// 처음에는 디스크로부터 객체를 읽어온다.
		image.display();
		System.out.println("");
		
		// 이미 존재하는 객체는 다시 디스크에서 읽지 않는다.
		image.display();
	
	}
}
