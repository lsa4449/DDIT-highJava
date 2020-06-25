package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class TcpClient2 {

	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("192.168.206.67", 7777);
		System.out.println("서버에 연결되었습니다.");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();

	}
}
