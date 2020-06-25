package kr.or.ddit.mp.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.Login.ILoginService;
import kr.or.ddit.mp.service.zone.IZoneService;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;

public class Login_Controller implements Initializable{

	@FXML Button btn_login;
	@FXML ImageView img_kakaologin;
	@FXML ImageView img_naverlogin;
	@FXML Button btn_join;
	@FXML Button btn_findPw;
	@FXML Button btn_findId;
	@FXML TextField txtf_id;
	@FXML TextField txtf_pw;
	
	private Registry reg;
	private ILoginService ilogin;
	
	List<MemberVO> list = new ArrayList<MemberVO>();
	List<ComVO> comlist = new ArrayList<ComVO>();
	private MemberVO mvo = new MemberVO();
	
	//관심지역 설정하기
	private IZoneService izs;
	ZoneVO zvo = new ZoneVO();
	ArrayList<ZoneVO> zlist = new ArrayList<>();
	
	//로그인유저 정보
	MemberVO mv = LoginSession.session;
	String user = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 try {
	         reg = LocateRegistry.getRegistry("localhost", 8429);
	         ilogin = (ILoginService) reg.lookup("userlogin");
	      } catch (RemoteException e) {
	         e.printStackTrace();
	      } catch (NotBoundException e) {
	         e.printStackTrace();
	      }
		 btn_login.setOnAction( e -> {
			 if (txtf_id.getText().equals("") || txtf_pw.getText().equals("") 
					 || txtf_id.getText() == null || txtf_pw.getText() == null) {
				 errMsg("로그인 에러!", "아이디 또는 비밀번호를 입력하지 않았습니다!");
	             return;
			}
			 
			 String mem_id = txtf_id.getText();
			 String mem_pw = txtf_pw.getText();
			 ArrayList<MemberVO> mList = new ArrayList<>();
			
			 MemberVO mvo = new MemberVO(); 
			 mvo.setMem_id(mem_id);
			 mvo.setMem_pw(mem_pw);
			
			 try {
				 mList = (ArrayList<MemberVO>) ilogin.memberLogin(mvo);
				 
			 } catch (RemoteException e1) {
				 e1.printStackTrace();
			 }
//			 String mid = mList.get(0).getMem_id();
//			 String mpw = mList.get(0).getMem_pw();
			 
			 
//			 if (mem_id.equals(mid) && mem_pw.equals(mpw)) {
			 if(mList.size() > 0 ) {	 
				infoMsg("로그인 성공!", "환영합니다! 즐거운 멜론플레이트 이용시간 되세요 :) ");
//				mvo.setMem_id(mid);
				
				MemberVO mvo2 = new MemberVO();
				mvo2.setMem_id(mem_id);
				
				try {
					list = (ArrayList<MemberVO>) ilogin.memNoSearch(mvo2);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				if(list.get(0).getMem_id().equals(txtf_id.getText())) {
				list.add(mvo2);
				LoginSession.session = list.get(0);
				
				
				try {
					comlist = ilogin.iscom(list.get(0).getMem_no());
				} catch (RemoteException e3) {
					e3.printStackTrace();
				}
				
				if(comlist.size() > 0) {
					LoginSession.comsession = comlist.get(0);
				}
				
				
				System.out.println("확인 "+LoginSession.session.getMem_no());
				
				// 메인 화면 접속
				/**
				 * 관심지역 설정화면 팝업창으로 변환
				*/
				try {
					reg = LocateRegistry.getRegistry("localhost", 8429);
					izs = (IZoneService) reg.lookup("zone");
					System.out.println("rmi 성!공!");
					
				} catch (RemoteException e2) {
					e2.printStackTrace();
				} catch (NotBoundException e2) {
					e2.printStackTrace();
				}
				
				zvo.setMem_no(LoginSession.session.getMem_no());
				
				try {
					zlist = (ArrayList<ZoneVO>) izs.selectZoneChoice(zvo);
					System.out.println(zlist.size());
					
					if(zlist.size()==0) {
						Stage oristage = (Stage) btn_login.getScene().getWindow();	
						oristage.close();
						Stage dialog = new Stage(StageStyle.UTILITY);
						
						dialog.initModality(Modality.APPLICATION_MODAL);
						dialog.initOwner(btn_login.getScene().getWindow());
						
						Parent parent = null;
						
						try {
							parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/zone/Zone_option.fxml"));
							
						}catch(IOException ex) {
							ex.printStackTrace();
						}
						
						Scene scene_pop = new Scene(parent);
						
						dialog.setScene(scene_pop);
						dialog.setResizable(false); 
						dialog.show();
						
						
					}else {
						DataModel.isFirstZone = false;
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/mp/main/top_member.fxml"));
						Parent root = null;
						try {
							root = loader.load();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						Scene scene = new Scene(root);
						Stage mainStage = new Stage();
						scene.getStylesheets().add(getClass().getResource("/tableview.css").toString());
						
						mainStage.setTitle("EAT, SHARE, BE HAPPY! 멜론플레이트 :) ");
						mainStage.setScene(scene);
						mainStage.show();
						Stage oristage = (Stage) btn_login.getScene().getWindow();	
						oristage.close();
							
					}
				} catch (RemoteException e4) {
					
					e4.printStackTrace();
				}
						
				
				
				
				}
			}else{
				infoMsg("에러", "아이디 또는 비밀번호가 일치하지 않습니다!");
	             return;
			}
		 });
		 
		 // 버튼 : 아이디 찾기
		 btn_findId.setOnAction( e1 -> {
			 
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMemId.fxml"));
				try {
					AnchorPane root = (AnchorPane) loader.load();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
		 });
		 
		 
		 // 버튼 : 비밀번호 찾기
		 btn_findPw.setOnAction( e1 -> {
			 
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMemPw.fxml"));
				try {
					AnchorPane root = (AnchorPane) loader.load();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		 });
		 
		 
		 //로그인 화면에서 회원가입 눌렀을 때 
		 btn_join.setOnAction(e -> {

			 Stage dialog = new Stage(StageStyle.UTILITY);
			 dialog.initModality(Modality.APPLICATION_MODAL);
			 dialog.initOwner(btn_join.getScene().getWindow());
			 dialog.setTitle("멜론플레이트 :) ______ 회원가입 *▽*");
			 Parent parent = null;

			 try {
				 parent = FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/join/join_select.fxml"));
			 }  catch (IOException ex) {
				 ex.printStackTrace();
			 }
			// try {
			// Parent root =
			// FXMLLoader.load(getClass().getResource("/kr/or/ddit/mp/view/join/join_select.fxml"));
			// Scene scene = new Scene(root);
			// Stage primaryStage =(Stage)btn_join.getScene().getWindow();
			// primaryStage.setScene(scene);
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }
			 Scene scene = new Scene(parent);
			 dialog.setScene(scene);

			 dialog.show();

		 });
		 
	 }

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
        errAlert.setTitle("오류");
        errAlert.setHeaderText(headerText);
        errAlert.setContentText(msg);
        errAlert.showAndWait();		
	}
	
	
	 private void infoMsg(String headerText, String msg) {
         Alert errAlert = new Alert(AlertType.INFORMATION);
         errAlert.setTitle("정보 확인");
         errAlert.setHeaderText(headerText);
         errAlert.setContentText(msg);
         errAlert.showAndWait();
      }
}