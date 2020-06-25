package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {

	// 클라이언트는 서버에 접속하여
	// 서버가 보내주는 파일을 D드라이브의 C_Lib폴더에 저장한다.
	private Socket socket;
	private InputStream in;
	private FileOutputStream fout;

	public void clientStart() {
		File file = new File("d:/C_Lib/Tulips.jpg");
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 다운로드 시작~");

			fout = new FileOutputStream(file);
			in = socket.getInputStream();

			byte[] tmp = new byte[1024];
			int length = 0;
			while ((length = in.read(tmp)) != -1) {
				fout.write(tmp, 0, length);
			}

			// flush(): 버퍼에 있는 데이터를 모두 처리하는 메소드
			// write()한 파일을 다시 읽을 때 사용
			fout.flush();
			System.out.println("파일 다운로드 완료~");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

}
