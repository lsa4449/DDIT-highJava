package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileClient {

	public static final int BUFFER_SIZE = 10000;

	public static void main(String[] args) throws IOException {

		int port = 8888;

		long fileSize;
		long totalReadBytes = 0;

		byte[] buffer = new byte[BUFFER_SIZE];
		int nReadSize = 0;
		System.out.println("파일 수신 대기중!!");

		DatagramSocket ds = new DatagramSocket(port);
		FileOutputStream fos = new FileOutputStream("d:/C_Lib/Tulips.jpg");
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		String str = new String(dp.getData()).trim();

		if (str.equals("start")) { // sender에서 전송을 시작한 경우
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			double startTime = System.currentTimeMillis();

			while (true) {
				ds.receive(dp);
				str = new String(dp.getData()).trim();
				nReadSize = dp.getLength();
				fos.write(dp.getData());
				totalReadBytes += nReadSize;
				System.out.println("진행중 : " + totalReadBytes + "/" + fileSize + "Bytes("
						+ (totalReadBytes * 100 / fileSize) + "%)");

				if (totalReadBytes >= fileSize) {
					break;
				}
			}

			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;

			System.out.println("걸린 시간 : " + diffTime + "sec");
			System.out.println("평균 속도 : " + transferSpeed + "KB/s");
			System.out.println("전송 완료~~~~");

			fos.close();
			ds.close();

		} else {
			System.out.println("수신 처리 불가!!");
			fos.close();
			ds.close();
		}
	}
}
