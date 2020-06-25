package kr.or.ddit.structural.decorator;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("원을 그린다.");
	}

}
