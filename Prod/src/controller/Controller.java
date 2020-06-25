package controller;

import java.util.Scanner;

import service.Service;

public class Controller {
	
		private static Controller controller;
		private Service service;

		private Controller() {
			service = Service.getInstance();
		}

		public static Controller getInstance() {
			if (controller == null) {
				controller = new Controller();
			}
			return controller;
		}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("번호선택(1 or 2 or 3");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				controller.prodList();
				break;
			case 2:
				System.out.print("id를 입력하세요 : ");
				String id = sc.nextLine();
				controller.prodInfo(id);
				break;
			case 3:
				System.exit(0);
			}
		}
	}

	private void prodInfo(String id) {
		System.out.println(service.prodInfo(id));
	}

	void prodList() {
		System.out.println(service.prodList());
		
	}

}
