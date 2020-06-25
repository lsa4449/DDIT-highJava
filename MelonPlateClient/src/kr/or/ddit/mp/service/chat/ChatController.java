package kr.or.ddit.mp.service.chat;

import java.net.URL;
import java.nio.channels.ServerSocketChannel;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController implements Initializable{

	@FXML private TextArea taChatList;
	@FXML private TextField tfChat;
	
	private String nickName; // 대화명
	
	private ChatServer server; // 원격 RMI 채팅 서비스 
	private ChatClientImpl chatClientImpl; // 원격객체
	
	
	public void setChatClientImpl(ChatClientImpl chatClientImpl) {
		this.chatClientImpl = chatClientImpl;
	}

	/**
	 * 초기화 메서드
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	/**
	 * 닉네임(대화명) 설정하기
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * RMI 서버 설정하기
	 * @param server
	 */
	public void setServer(ChatServer server) {
		this.server = server;
	}

	/**
	 * 메시지 전송시 호출되는 메서드
	 * @param event
	 * @throws RemoteException
	 */
	@FXML
	public void sendMessage(ActionEvent event) throws RemoteException{
		String message = tfChat.getText();
		server.say(message, nickName);
		tfChat.setText("");
	}
	
	/**
	 * 텍스트 박스에 메시지 출력하기
	 * @param message
	 */
	public void showMsg(String message) {
		taChatList.appendText(message+ "\n");
	}
	
	/**
	 * RMI 서버 접속 해제
	 * @param event
	 * @throws RemoteException
	 */
	@FXML 
	public void disconnect(ActionEvent event) throws RemoteException {
		
		server.disconnect(chatClientImpl, nickName);
		
		chatClientImpl.getPrimaryStage().close(); // 창 닫기
		
	}
	
}
