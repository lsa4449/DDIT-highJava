package kr.or.ddit.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable {

	private ChatServer chatServer;
	private static String name;

	protected ChatClientImpl(String name) throws RemoteException {
		super();
		ChatClientImpl.name = name;

		// 등록된 서버를 찾기 위해 Registry객체를 생성한 후
		// 사용할 객체를 불러온다.
		Registry reg = LocateRegistry.getRegistry("localhost", 8888);
		try {
			chatServer = (ChatServer) reg.lookup("chatServer");
			chatServer.addClient(this, name);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printMessage(String message) throws RemoteException {

		System.out.println(message);
	}

	@Override
	public void run() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String message = null;
			while ((message = br.readLine()) != null) {
				chatServer.say("" + name + " : " + message);

				if (message.equals("exit")) {
					chatServer.disconnect(this, name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String name = JOptionPane.showInputDialog("대화명을 입력하세요~");
		new Thread(new ChatClientImpl(name)).start();
	}
}
