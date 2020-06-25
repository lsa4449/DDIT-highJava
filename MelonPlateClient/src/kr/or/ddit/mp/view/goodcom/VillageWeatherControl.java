package kr.or.ddit.mp.view.goodcom;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VillageWeatherControl {
	 
	  public static void main(String[] args) {
	    // 동내 기상 JSON데이터를 얻기위해 필요한 요청변수를 입력해줍니다.
	    //baseDate : 기준날짜, baseTime : 기준시간, x : 경도, y : 위도 참고문서를 확인하세요
	    int x = 92;
	    int y = 131;
	    
	    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
	    		
	    Date time = new Date();
	    		
	    String time1 = format1.format(time);
	    		
	    System.out.println(time1);
	    
		String baseDate = time1;
		String baseTime = "0800";
//		String baseTime = "2300"; 
		//0200, 0500, 0800, 1100, 1400, 2000, 2300
	      // 기상데이터를 얻어오는 객체를 생성
	    VillageWeatherJSON vwJson = new VillageWeatherJSON();
	    // 기상데이터를 JSON형태로 받아 VillageWeather에 저장
	    VillageWeatherVO vw = vwJson.getVillageWeather(baseDate, baseTime, x, y);
	    // 데이터베이스에 접속에 관련하는객체를 만들고 데이터베이스에 입력
	    String sky = vw.getSky();
	    String rain = vw.getPty();
	    String cel = vw.getT3h();
	    
	    System.out.println(sky + rain +cel);
	  }
	}



