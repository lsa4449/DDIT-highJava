package kr.or.ddit.structural.proxy;

public class RealImage implements Image {
	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	private void loadFromDisk(String fileName) {
		System.out.println("파일 로드 중... : " + fileName);
		System.out.println("파일 로드 완료~!");
	}

	@Override
	public void display() {
		System.out.println("화면 출력하기 : " + fileName);
	}

}
