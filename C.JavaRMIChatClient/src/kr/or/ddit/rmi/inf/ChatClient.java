package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {

	// 메시지 보내기
	public void printMessage(String message) throws RemoteException;
	
	
	
}
