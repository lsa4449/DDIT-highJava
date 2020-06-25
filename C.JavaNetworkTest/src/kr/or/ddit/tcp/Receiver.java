package kr.or.ddit.tcp;
/**
 * 이 클래스는 소켓에서 메시지를 받아서 화면에 출력하는 역할 담당
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
	Socket socket;
	DataInputStream dis;

	public Receiver(Socket socket) {
		this.socket = socket;

		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (dis != null) { // dis 객체가 제대로 생성된 경우..
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
