package kr.or.ddit.mp.view.review;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.review.IReviewService;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.review.ReviewVO;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReviewHolicController implements Initializable{

	@FXML AnchorPane ap_write;
	
	@FXML Label lab_percent;
	@FXML Label lab_nick;
	@FXML Label lab_back;
	@FXML Label lab_joindate;
	
	@FXML ImageView img_gage;
	
	
	//리뷰 서비스 
	private Registry reg;
	private IReviewService irs;
	
	//글 전체 정보를 담고 있는 리스트
	private ArrayList<ReviewVO> list = new ArrayList<>();
	
	//로그인유저정보 받기
  	MemberVO mv = LoginSession.session;
  	String user;
	ReviewVO vo = new ReviewVO();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			irs = (IReviewService) reg.lookup("review");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		user = mv.getMem_no();
		vo.setMem_no(user);
		
		int holic_gage = 0;
		try {
			list = (ArrayList<ReviewVO>) irs.checkHolic(vo);
			
			if(list.size()!=0) {
				lab_nick.setText(list.get(0).getMem_nick()+"님");
				lab_percent.setText(list.get(0).getHolic()+"%");
				String num_holic = list.get(0).getHolic();
				
				holic_gage = Integer.parseInt(num_holic)/10;
				
			}
			
			if(list.size()==0) {
				lab_nick.setText(mv.getMem_nick()+"님");
				lab_percent.setText("0%");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		//확인하기
		System.out.println(holic_gage);
		
		if(holic_gage==10) {
			img_gage.setImage(new Image(getClass().getResource("100.png").toString()));
		}else if(holic_gage==9) {
			img_gage.setImage(new Image(getClass().getResource("90.png").toString()));
		}else if(holic_gage==8) {
			img_gage.setImage(new Image(getClass().getResource("80.png").toString()));
		}else if(holic_gage==7) {
			img_gage.setImage(new Image(getClass().getResource("70.png").toString()));
		}else if(holic_gage==6) {
			img_gage.setImage(new Image(getClass().getResource("60.png").toString()));
		}else if(holic_gage==5) {
			img_gage.setImage(new Image(getClass().getResource("50.png").toString()));
		}else if(holic_gage==4) {
			img_gage.setImage(new Image(getClass().getResource("40.png").toString()));
		}else if(holic_gage==3) {
			img_gage.setImage(new Image(getClass().getResource("30.png").toString()));
		}else if(holic_gage==2) {
			img_gage.setImage(new Image(getClass().getResource("20.png").toString()));
		}else if(holic_gage==1) {
			img_gage.setImage(new Image(getClass().getResource("10.png").toString()));
		}else if(holic_gage==1) {
			img_gage.setImage(new Image(getClass().getResource("0.png").toString()));
		}
		
	}

	
	
}






























