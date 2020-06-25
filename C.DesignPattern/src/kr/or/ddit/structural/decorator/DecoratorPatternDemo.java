package kr.or.ddit.structural.decorator;

public class DecoratorPatternDemo {

	public static void main(String[] args) {
		Shape circle = new Circle();

		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("평범한 원 그리기 시작~");
		circle.draw();

		System.out.println("빨간 경계선을 가진 원 그리기 시작~");
		redCircle.draw();

		System.out.println("빨간 경계선을 가진 사각형 그리기 시작~");
		redRectangle.draw();

	}
}
