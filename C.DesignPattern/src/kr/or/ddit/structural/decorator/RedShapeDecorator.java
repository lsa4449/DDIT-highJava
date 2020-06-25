package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {
		decoratedShape.draw(); // 원래 객체 기능 호출
		
		setRedBorder();
	}

	private void setRedBorder() {
		System.out.println("경계선을 빨간색으로 칠한다!!");
	}
}
