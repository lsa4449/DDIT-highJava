package kr.or.ddit.creational.factory;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("정사각형 안에서 : draw()메서드 호출");
	}

}
