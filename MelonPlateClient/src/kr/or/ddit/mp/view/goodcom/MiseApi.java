package kr.or.ddit.mp.view.goodcom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MiseApi {

	final static String serviceKey = "%2BelyhP1JbDAF3A4XwtJkgiu3vJM0qJJ%2FipaqoX9KzSteZx9HwFeQ9U8lVNTggqLgkpgbwZXxFsYQ7P2lQ5C4Bg%3D%3D";
	
	public MiseVO getMise(String location) {
		MiseVO mise = new MiseVO();
		
//		String loca = "수내동";

		BufferedReader br = null;
		// DocumentBuilderFactory 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {
			// OpenApi호출
			String urlstr = "http://openapi.airkorea.or.kr/"
					+ "openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"
					+ "?stationName="+location+"&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey="+serviceKey+"&ver=1.3";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

			// 응답 읽기
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line.trim();// result = URL로 XML을 읽은 값
			}

			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(result));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			// XPathExpression expr = xpath.compile("/response/body/items/item");
			XPathExpression expr = xpath.compile("//items/item");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			NodeList child = nodeList.item(0).getChildNodes();
			
			
			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);
				System.out.println("현재 노드 이름 : " + node.getNodeName());
				System.out.println("현재 노드 타입 : " + node.getNodeType());
				System.out.println("현재 노드 값 : " + node.getTextContent());
				System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
				System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
				System.out.println("");
				
				
				if(node.getNodeName().equals("pm10Value")) {
					mise.setPm10Value(node.getTextContent());
				}
				if(node.getNodeName().equals("pm10Grade")) {
					mise.setPm10Grade(node.getTextContent());
				}
				if(node.getNodeName().equals("pm10Value24")) {
					mise.setPm10Value24(node.getTextContent());
				}
				if(node.getNodeName().equals("pm25Value")) {
					mise.setPm25Value(node.getTextContent());
				}
				if(node.getNodeName().equals("pm25Grade")) {
					mise.setPm25Grade(node.getTextContent());
				}
				if(node.getNodeName().equals("pm25Value24")) {
					mise.setPm25Value24(node.getTextContent());
				}
				
			}
			
//			System.out.println(mise.getPm10Value());
//			System.out.println(mise.getPm10Grade());
//			System.out.println(mise.getPm10Value24());
//			System.out.println(mise.getPm25Value());
//			System.out.println(mise.getPm25Grade());
//			System.out.println(mise.getPm25Value24());
			
			/*
			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				for (int j = 0; j < child.getLength(); j++) {
					Node node = child.item(j);
					System.out.println("현재 노드 이름 : " + node.getNodeName());
					System.out.println("현재 노드 타입 : " + node.getNodeType());
					System.out.println("현재 노드 값 : " + node.getTextContent());
					System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
					System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
					System.out.println("");
				}
			}
			*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return mise;

	}


}
