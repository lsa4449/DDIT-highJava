package thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import vo.ClientVO;

public class ChatServerRunnable implements Runnable{

	//UDP연결을 담당할 소켓
	private DatagramSocket socket = null;
	//클라이언트의 ip주소 리스트 - MainController의 clientList와 같은 주소를 바라보고 있음
	private ObservableList<String> clientList;
	private Map<String, ClientVO> clientMap;
	//서버 실행 여부
	private boolean isServerOn = true;
	
	//생성자
	public ChatServerRunnable(ObservableList<String> clientList, Map<String, ClientVO> clientMap){
		try {
			//객체가 생성될 때 소켓을 7777포트로 초기화
			this.socket = new DatagramSocket(7777);
			//소켓의 타임아웃을 0.5초로 설정 - 클라이언트로부터 데이터가 들어오지 않은채로
			//0.5초가 지나면 타임아웃 익셉션을 발생시키고 넘어가게 됨
			this.socket.setSoTimeout(500);
			//MainController로부터 받은 클라이언트 정보 맵
			this.clientList = clientList;
			this.clientMap = clientMap;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(isServerOn){
			byte[] inMsg = new byte[100];
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);
			try {
				socket.receive(inPacket);
				final InetAddress address = inPacket.getAddress();
				final int port = inPacket.getPort();
				
				boolean isExist = clientList.contains(address.getHostName());
			
				if(!isExist){ // 목록에 존재하지 않는 사용자라면..
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							clientList.add(address.getHostName()); // JavaFx Application 쓰레드 관련 작업임.
						}
					});
					
					ClientVO vo = new ClientVO(address.getHostAddress(), 
												port, 
												new String(inPacket.getData()).trim() // 대화명에서 불필요한 공백 제거.
												);
					clientMap.put(address.getHostName(), vo);
					
				}else{ // 목록에 존재하는 사용자인 경우...(이미 채팅중인 사용자인 경우...)
				
					System.out.println(new String(inPacket.getData()));
					
					Iterator<String> it = clientMap.keySet().iterator();
					
					ClientVO senderVO =  clientMap.get(address.getHostName());
					
					while(it.hasNext()) {
						String ipAddr = it.next();
						ClientVO vo = clientMap.get(ipAddr);
						InetAddress ipAddress = InetAddress.getByName(vo.getIpAddr());
						DatagramPacket outPacket = null;
						if(address.getHostName().equals(vo.getIpAddr()) 
							&& (port != vo.getPortNum())) { // 아이피주소는 동일한데 포트번호가 다른경우...
							vo.setPortNum(port);
							clientMap.put(ipAddr, vo); // 기존 정보 갱신
						}
						
						String sendMsg = "[" + senderVO.getChatName() + "] " + new String(inPacket.getData());
						
						System.out.println("메시지 : " + sendMsg);
						outPacket = new DatagramPacket(sendMsg.getBytes(), sendMsg.getBytes().length, ipAddress, vo.getPortNum());
						socket.send(outPacket); // 접속한 클라이언트에게 메시지 전송
					}
				}
				
				//Thread.sleep(500);
			} catch(SocketTimeoutException e){	
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void turnOffServer(){
		isServerOn = false;
	}
}
