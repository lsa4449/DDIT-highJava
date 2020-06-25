package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {

	// 사용자 추가
	public void addClient(ChatClient client, String name) throws RemoteException;

	public void disconnect(ChatClient client, String name) throws RemoteException;

	public void say(String messsage) throws RemoteException;

}
