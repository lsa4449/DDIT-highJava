package kr.or.ddit.mp.view.login;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kr.or.ddit.mp.service.Login.ILoginService;
import kr.or.ddit.mp.vo.member.MemberVO;

public class SearchMemPw_Controller implements Initializable{
	
	
	@FXML Button btn_find;
	@FXML TextField txtf_name;
	@FXML TextField txtf_hp;
	@FXML TextField txtf_id;
	
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
			 if (txtf_id.getText().equals("") || txtf_name.getText().equals("") || txtf_hp.getText().equals("")
					 || txtf_id.getText() == null || txtf_name.getText() == null || txtf_hp.getText() == null) {
				errMsg("비밀번호 찾기 에러!", "입력하지 않은 정보가 있습니다.!");
				return;
			}
			 
			 String mem_id = txtf_id.getText();
			 String mem_name = txtf_name.getText();
			 String mem_hp = txtf_hp.getText();
			 
			 ArrayList<MemberVO> mList = new ArrayList<>();
				
			 MemberVO mvo = new MemberVO(); 
			 mvo.setMem_id(mem_id);
			 mvo.setMem_name(mem_name);
			 mvo.setMem_hp(mem_hp);
			 
			 
			 try {
				mList = (ArrayList<MemberVO>) ilogin.pwSearch(mvo);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			 
			 if (mList.size() > 0 ) {
				 
					String host = "smtp.naver.com";
					final String user = "jocoon1218@naver.com";
					final String password = "abcdef";
					
					infoMsg("비밀번호찾기 성공!", "귀하의 임시 비밀번호를 이메일로 발송하였습니다");
					Stage stage = (Stage) btn_find.getScene().getWindow();
					 stage.close();
					System.out.println(txtf_id.getText()+"로 이메일 발송");
					
					String to = txtf_id.getText();
					
					Properties props = new Properties();
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.auth", "true");

					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});
					
					try {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(user));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

						message.setSubject("[JavaMail 임시 비밀번호] MelonPlate");

						message.setText("귀하의 임시 비밀번호는" + mList.get(0).getMem_pw()+"입니다.");

						// send the message
						Transport.send(message);
						System.out.println("message sent successfully...");

					} catch (MessagingException e4) {
						e4.printStackTrace();
					}
					 
			}else {
				errMsg("비밀번호 찾기 에러!","입력하신 정보와 일치하는 아이디의 비밀번호가 존재하지 않습니다.");
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
