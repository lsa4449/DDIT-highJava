package kr.or.ddit.mp.view.login;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kr.or.ddit.mp.service.Login.ILoginService;
import kr.or.ddit.mp.vo.member.MemberVO;

public class SearchId_Controller implements Initializable {

	@FXML Button btn_find;
	@FXML TextField txtf_name;
	@FXML TextField txtf_ph;

	
	private Registry reg;
	private ILoginService ilogin;
	
	List<MemberVO> list = new ArrayList<MemberVO>();
	private MemberVO mvo = new MemberVO();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 try {
	         // 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
	         reg = LocateRegistry.getRegistry("localhost", 8429);
	         // 설정한 서버를 찾는 메소드 lookup
	         ilogin = (ILoginService) reg.lookup("userlogin");
	      } catch (RemoteException e) {
	         e.printStackTrace();
	      } catch (NotBoundException e) {
	         e.printStackTrace();
	      }
		 
		 btn_find.setOnAction(e -> {
			 if (txtf_name.getText().equals("") || txtf_ph.getText().equals("")
					 || txtf_name.getText() == null || txtf_ph.getText() == null) {
				 errMsg("아이디 찾기 에러!!", "입력하지 않은 정보가 있습니다!");
	             return;
			}
			 
			 
			 String mem_name = txtf_name.getText();
			 String mem_hp = txtf_ph.getText();
			 
			 ArrayList<MemberVO> mList = new ArrayList<>();
			
			 MemberVO mvo = new MemberVO(); 
			 mvo.setMem_name(mem_name);
			 mvo.setMem_hp(mem_hp);
			 
			System.out.println(mvo.getMem_name());
			

			 try {
				 mList = (ArrayList<MemberVO>) ilogin.idSearch(mvo);
				 
			 } catch (RemoteException e1) {
				 e1.printStackTrace();
			 }
			 
			 System.out.println(mList.size());
			 
			 if (mList.size() > 0) {
				 infoMsg("입력하신 정보에 일치하는 아이디 입니다.",  mList.get(0).getMem_id());
				 Stage stage = (Stage) btn_find.getScene().getWindow();
				 stage.close();
//				 mvo.setMem_name(mname);
//				 mvo.setMem_hp(mhp);
			}else {
				 errMsg("아이디 찾기 에러!!", "해당 정보에 해당하는 ID가 존재하지 않습니다.");
				 return;
			}
			 
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
         errAlert.setTitle("MelonPlate Id 찾기");
         errAlert.setHeaderText(headerText);
         errAlert.setContentText(msg);
         errAlert.showAndWait();
      }

}
