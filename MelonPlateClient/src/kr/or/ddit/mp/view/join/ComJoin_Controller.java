package kr.or.ddit.mp.view.join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.mp.service.join.IComJoinService;
import kr.or.ddit.mp.service.join.IJoinService;
import kr.or.ddit.mp.vo.member.ComVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.member.ReadyComVO;
import kr.or.ddit.mp.vo.zipcode.ZipcodeVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

public class ComJoin_Controller implements Initializable {

	@FXML AnchorPane goodcom_anchor;
	@FXML TextField txtf_email;
	@FXML Button btn_idchk;
	@FXML PasswordField txtf_pw;
	@FXML PasswordField txtf_pw2;
	@FXML TextField txtf_name;
	@FXML TextField txtf_birth;
	@FXML TextField txtf_phone;
	@FXML TextField txtf_nick;
	@FXML Button btn_nickchk;
	@FXML TextField txtf_zipcode;
	@FXML Button btn_findzip;
	@FXML TextField txtf_addr;
	@FXML Button btn_submit;
	@FXML Button btn_cancel;
	@FXML TextField txtf_key; 
	@FXML TextField txtf_menu; 
	@FXML ComboBox<String> com_opentime;  
	@FXML ComboBox<String> com_closetime;  
	@FXML ComboBox<String> com_parking;  
	
	
	
	private Registry reg;
	private IComJoinService icjoin;
	private IJoinService ijs;

	List<ReadyComVO> list = new ArrayList<ReadyComVO>();
	private ReadyComVO mvo = new ReadyComVO();
	
	int chk_cnt = 0;
	int pattern_cnt = 0;
	
	//캡차관련 변수----------------------------------
	private boolean captchaFlag = false;
	private String captchaKey = "";
	File f = null; // captchaimg파일
	String captchaImg = ""; // captcha img 파일이름.
	//-----------------------------------------------
	
	boolean result = false;
	int no_cnt = 0;
	
	//팝업창에서 사용되는 변수
	String zip = null;
	String gungu = null;
	String sido = null;
	String dong = null;
	
	//입력값 담은 변수
	String mem_id  = null;
	
	String mem_pw  = null;
	String mem_pw2 =  null;
	
	String mem_nick = null;
	String mem_name = null;
	
	String mem_birth = null;
	String mem_hp    = null;
	@FXML Button btn_codechk;
	@FXML TextField txtf_codechk;
	@FXML TextField txtf_category;
	@FXML TextField txtf_addr1;
	@FXML TextField txtf_comzipcode;
	@FXML TextField txtf_comtel;
	@FXML Button btn_findcomzip;
	@FXML TextField txtf_comname;
	@FXML Button btn_comidchk;
	@FXML TextField txtf_comid;
	@FXML TextField txtf_foodprice;
	@FXML Button btn_refresh;
	@FXML ImageView img_auto;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		captchaKey = captchaKey();
		captchaImage(captchaKey);
		
		com_opentime.getItems().addAll("9","10","11","12","13","14","15","16","17","18","19");
		com_closetime.getItems().addAll("14","15","16","17","19","20","21","22","23","24");
		com_parking.getItems().addAll("Y","N");
		
		
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry("localhost", 8429);
			// 설정한 서버를 찾는 메소드 lookup
			icjoin = (IComJoinService) reg.lookup("comjoin");
			ijs = (IJoinService) reg.lookup("userjoin");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		btn_idchk.setOnAction(e -> {
			
			/**
			 * 아이디 정규식 소문자. 숫자. _-포함 소문자와 . 2글자 이상 6글자이하
			 */
			
			mem_id = txtf_email.getText();
			
			Pattern p1 = Pattern.compile("\\w*@[a-z]*\\.[a-z]{3}");
			Matcher m1 = p1.matcher(mem_id);
			result = m1.matches();
			
			if (mem_id.equals("") || mem_id == null) {
				errMsg("입력체크", "아이디를 입력해주십시오.");
				no_cnt++;
				return;
			}
			
			if (result) {
				pattern_cnt++;
				System.out.println("아이디 정규화" + pattern_cnt);
			} else {
				errMsg("아이디 오류", "형식에 맞지 않는 아이디 입니다!");
				return;
				
			}

			ArrayList<MemberVO> mList = new ArrayList<>();

			MemberVO mvo = new MemberVO();
			mvo.setMem_id(mem_id);

			try {
				mList =  (ArrayList<MemberVO>) ijs.selectId(mvo);
				
				if (mList.size() == 0) {
					infoMsg("중복체크", "사용하실 수 있는 아이디 입니다.");
					chk_cnt++;
					System.out.println("아이디 중복체크" + chk_cnt);
				} else {
					errMsg("중복체크", "이미 사용하고 있는 아이디 입니다.");
					return;
				}
				
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}

			
		});
		
		
		btn_nickchk.setOnAction(e -> {

			mem_nick = txtf_nick.getText();
			
			Pattern p10 = Pattern.compile("^[가-힣]*$");
			Matcher m10 = p10.matcher(mem_nick);
			result = m10.matches();
			
			if (mem_nick.equals("") || mem_nick == null) {
				errMsg("에러!", "닉네임을 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}

			if (result) {
				pattern_cnt++;
				
				System.out.println("닉네임 정규화" + pattern_cnt);
			} else if (result == false){
				errMsg("에러!", "닉네임은 한글만 사용 할 수 있습니다.");
				return;
				
			}
			
			ArrayList<MemberVO> mList = new ArrayList<>();

			MemberVO mvo = new MemberVO();
			mvo.setMem_nick(mem_nick);

			try {
				mList = (ArrayList<MemberVO>) ijs.selectNick(mvo);
				
				if (mList.size() == 0) {
					infoMsg("중복체크", "사용하실 수 있는 닉네임 입니다.");
					chk_cnt++;
					System.out.println("닉네임 중복체크" + chk_cnt);
					
				} else {
					errMsg("중복체크", "이미 사용하고 있는 닉네임 입니다.");
					return;
				}
				
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}

			System.out.println(mList.size());
			

		});
		
		
		btn_findzip.setOnAction(e -> {
			
			Parent parent = null;
			
			try {
	            parent = FXMLLoader.load(getClass().getResource("zipcode.fxml"));
	         } catch (IOException e3) {
	            e3.printStackTrace();
	         }
	         
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(btn_findzip.getScene().getWindow());
			dialog.setTitle("우편번호 검색");
			
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false); 
			dialog.show();

	        TableView<ZipcodeVO> tb_zipcode = (TableView<ZipcodeVO>) parent.lookup("#tb_zipcode");
	        TextField txtf_dong 			= (TextField) parent.lookup("#txtf_dong");
			Button btn_zip_ok 				= (Button) parent.lookup("#btn_zip_ok");
	        
	        tb_zipcode.setOnMouseClicked(event_pop->{
	        	
	            zip 	 = tb_zipcode.getSelectionModel().getSelectedItem().getZipcode();
	            gungu 	 = tb_zipcode.getSelectionModel().getSelectedItem().getGugun();
	            sido  	 = tb_zipcode.getSelectionModel().getSelectedItem().getSido();
	            dong  	 = tb_zipcode.getSelectionModel().getSelectedItem().getDong();
	            
	            btn_zip_ok.setOnAction(event_pop2->{
		        	
	            	txtf_zipcode.setText(zip);
		            txtf_addr.setText(sido+" "+gungu+" "+dong);
		            dialog.close();
		            
		        });
	            
	         });
	        
	       
	        
		});
		
		btn_comidchk.setOnAction(e->{
			String com_id = txtf_comid.getText();
			Pattern p123 = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{5}");
			Matcher m123 = p123.matcher(com_id);
			result = m123.matches();
			
			
			if (com_id.equals("") || com_id == null) {
				errMsg("에러", "사업자 번호를 입력해주세요");
				no_cnt++;
				return;
			}else if (result == false) {
				errMsg("에러", "사업자 번호가 형식에 맞지 않습니다. '-'를 포함하여 입력해 주세요");
				return;
			}
			
			ArrayList<ComVO> cList = new ArrayList<>();
			ComVO rvo = new ComVO();
			rvo.setCom_id(com_id);
			
			try {
				cList = (ArrayList<ComVO>) icjoin.selectComId(rvo);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			if (cList.size() == 0) {
				infoMsg("중복체크", "사용가능한 사업자 번호입니다.");
				chk_cnt++;
				pattern_cnt++;
			}else {
				errMsg("에러!", "이미 등록된 사업자 번호입니다.");
				return;
			}
			
		});
		
		
		
		btn_findcomzip.setOnAction(e -> {
			Parent parent = null;
			
			try {
	            parent = FXMLLoader.load(getClass().getResource("zipcode.fxml"));
	         } catch (IOException e3) {
	            e3.printStackTrace();
	         }
	         
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(btn_findzip.getScene().getWindow());
			dialog.setTitle("우편번호 검색");
			
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false); 
			dialog.show();

	        TableView<ZipcodeVO> tb_zipcode = (TableView<ZipcodeVO>) parent.lookup("#tb_zipcode");
	        TextField txtf_dong 			= (TextField) parent.lookup("#txtf_dong");
			Button btn_zip_ok 				= (Button) parent.lookup("#btn_zip_ok");
	        
	        tb_zipcode.setOnMouseClicked(event_pop3->{
	        	
	            zip 	 = tb_zipcode.getSelectionModel().getSelectedItem().getZipcode();
	            gungu 	 = tb_zipcode.getSelectionModel().getSelectedItem().getGugun();
	            sido  	 = tb_zipcode.getSelectionModel().getSelectedItem().getSido();
	            dong  	 = tb_zipcode.getSelectionModel().getSelectedItem().getDong();
	            
	            btn_zip_ok.setOnAction(event_pop4->{
		        	
	            	txtf_comzipcode.setText(zip);
		            txtf_addr1.setText(sido+" "+gungu+" "+dong);
		            dialog.close();
		            
		        });
	            
	         });
			
		});
		
		btn_codechk.setOnAction(e-> {
			captchaCheck();
		});
		
		btn_refresh.setOnAction(e->{
			imgRefresh();
		});
		
		
		
		
		
		btn_submit.setOnAction(e -> {

			mem_id = txtf_email.getText().trim();
			mem_name = txtf_name.getText().trim();
			
			mem_nick = txtf_nick.getText().trim();
					
			mem_pw = txtf_pw.getText();
			mem_pw2 = txtf_pw2.getText();
			
			mem_birth = txtf_birth.getText().trim();
			mem_hp    = txtf_phone.getText();
			
			/**
			 * 비밀번호 정규식 최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함
			 */
			
			String mem_pw = txtf_pw.getText();
			String mem_pw2 = txtf_pw2.getText();
			Pattern p2 = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
			Matcher m2 = p2.matcher(mem_pw);
			result = m2.matches();
			if (mem_pw.equals(mem_pw2)) {
				if (result) {
					pattern_cnt++;

					System.out.println("비밀번호 정규화" + pattern_cnt);

				} else {
					errMsg("비밀번호 오류", "입력하신 비밀번호는 형식에 맞지 않은 비밀번호 입니다!");
					return;
				}
			} else {
				errMsg("비밀번호 오류", " 입력하신 비밀번호가 일치하지 않습니다!");
				return;
			}
			
			/**
			 * 이름 정규식 한글만 가능 /^[가-힣]+$/
			 */
			
			Pattern p3 = Pattern.compile("^[가-힣]*$");
			Matcher m3 = p3.matcher(mem_name);
			result = m3.matches();

			if (result) {
				pattern_cnt++;
				System.out.println("이름 정규화" + pattern_cnt);
			} else if (mem_name.equals("") || mem_name == null) {
				errMsg("에러!", "이름을 입력하지 않으셨습니다.");
				no_cnt++;
				return;
				
			} else if (result == false) {
				errMsg("에러!", "이름은 한글만 가능합니다.");
				return;
				
			}

			/**
			 * 생년월일 ex)941218 ==> '-' 쓰면 안됨 [0-9]{6}
			 */

			Pattern p4 = Pattern.compile("[0-9]{6}");
			Matcher m4 = p4.matcher(mem_birth);
			result = m4.matches();

			if (result) {
				pattern_cnt++;
				System.out.println("생년월일 정규화" + pattern_cnt);
			} else if (mem_birth.equals("") || mem_birth == null) {
				errMsg("에러!", "생년월일을 입력하지 않으셨습니다.");
				no_cnt++;
				return;
				
			} else if (result == false) {
				errMsg("에러", "생년월일을 형식에 맞게 입력하여 주십시오");
				return;
				
			}

			/**
			 * 핸드폰 번호 정규식 [0-9]{11}
			 */
			Pattern p5 = Pattern.compile("[0-9]{11}");
			Matcher m5 = p5.matcher(mem_hp);
			result = m5.matches();

			if (result) {
				pattern_cnt++;
				System.out.println("핸드폰 정규화" + pattern_cnt);
			} else if (mem_hp.equals("") || mem_hp == null) {
				errMsg("에러!", "핸드폰 번호를 입력하지 않으셨습니다!");
				no_cnt++;
				return;
				
			} else if (result == false) {
				errMsg("에러!", "핸드폰 번호를 입렬할 땐 '-' 을 사용하지 말아주세요.");
				return;
				
			}
			
			
			
			/**
			 * 업체 전화번호
			 * 숫자2~3자리 - 숫자3~4자리 - 숫자4자리
			 * '-'을 포함해야함
			 * return str.matches("(01[016789])-(\\d{3,4})-(\\d{4})");
			 */
			
			String com_tel = txtf_comtel.getText();
			Pattern p7 = Pattern.compile("[0-9]{10}");
			Matcher m7 = p7.matcher(com_tel);
			result = m7.matches();
			
			if (com_tel.equals("") || com_tel == null) {
				errMsg("에러!", "업체 전화번호를 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}
			
			if (result) {
				pattern_cnt++;
			}else {
				errMsg("에러", "전화번호를 입력할 시 '-'을 제외하고 입력해주세요");
				return;
			}
			
			
			/**
			 * 정보 입력 안했을때 처리
			 */
			
			String com_name = txtf_comname.getText();
			String com_zipcode = txtf_comzipcode.getText();
			String com_addr = txtf_addr.getText();
			String com_cat = txtf_category.getText();
			String com_foodprice = txtf_foodprice.getText();
			if (com_name.equals("") || com_name == null) {
				errMsg("에러!", "업체이름을 등록하지 않으셨습니다!");
				no_cnt++;
				return; 
			}
			
			else if (com_zipcode.equals("") || com_zipcode == null) {
				errMsg("에러!", "우편번호를 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}
			
			else if (com_addr.equals("") || com_addr == null) {
				errMsg("에러!", "주소를 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}
				
			else if (com_cat.equals("") || com_cat == null) {
				errMsg("에러!", "카테고리를 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}
			
			else if (com_foodprice.equals("") || com_foodprice == null) {
				errMsg("에러!", "음식가격을 입력하지 않으셨습니다!");
				no_cnt++;
				return;
			}
			
			
			
			if (chk_cnt == 4 && pattern_cnt == 8 && no_cnt == 0) {
				
			ReadyComVO rcomvo = new ReadyComVO();
			
				int cnt = 0;
				
				
				
				
				
				rcomvo.setMem_id(mem_id); //1
				rcomvo.setMem_pw(mem_pw); //2
				rcomvo.setMem_name(mem_name); //3
				rcomvo.setMem_birth(mem_birth); //4
				rcomvo.setMem_email(mem_id); //email mem_id로 추가 //삽입에러의원인이엇음 //5
				rcomvo.setMem_hp(mem_hp); //6
				rcomvo.setMem_nick(mem_nick); //7
				rcomvo.setMem_addr(txtf_addr.getText()); //8
				rcomvo.setMem_zipcode(txtf_zipcode.getText()); //9
				rcomvo.setCom_name(txtf_comname.getText());
				rcomvo.setCom_name(com_name);
				rcomvo.setCom_tel(com_tel);
				rcomvo.setCom_zipcode(com_zipcode);
				rcomvo.setCom_addr(com_addr);
				rcomvo.setCom_cat(com_cat);
				rcomvo.setCom_id(txtf_comid.getText());
				
				
				
				
				rcomvo.setCom_key(txtf_key.getText());
				rcomvo.setCom_menu(txtf_menu.getText());
				rcomvo.setCom_foodprice(com_foodprice);
				rcomvo.setCom_opentime(com_opentime.getValue());
				rcomvo.setCom_closetime(com_closetime.getValue());
				rcomvo.setCom_parking(com_parking.getValue());
				
				try {
					cnt = icjoin.insertReadyComMember(rcomvo);
					if (cnt > 0) {
						infoMsg("중복체크", "회원정보 입력이 모두 완료되었습니다.");
						 Stage stage = (Stage) btn_submit.getScene().getWindow();
						 stage.close();
						
					} else {
						errMsg("에러", "회원가입에 문제가 있습니다. :( ");
						return;
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}else {
				errMsg("회원가입 실패", "입력하신 정보 중 올바르지 않은 정보가 있습니다. 아이디, 닉네임, 캡차, 사업자번호 확인 버튼을 다시 눌러주세요");
				//체크하는 cnt들 초기화
				pattern_cnt = 0;
				chk_cnt = 0;
				no_cnt = 0;
			}
		});
		
		btn_cancel.setOnAction(e->{
			Stage stage = (Stage) btn_cancel.getScene().getWindow();
			 stage.close();
		});
		
	
	}
	
	
	
	public void captchaCheck() {
		
		String result1 = captchaResult(captchaKey, txtf_codechk.getText());
		if(txtf_codechk.getText().equals("")) {
			captchaFlag = false;
			errMsg("에러!", "좌측에 보이는 이미지의 문자를 입력해주세요");
		}else if(result1.equals("false")) {
			imgRefresh();
			captchaFlag = false;
			errMsg("에러!", "좌측에 보이는 이미지의 문자와 입력하신 문자가 다릅니다!");
		}else if(result1.equals("true,")) {
			captchaFlag = true;
			infoMsg("성공!", "일치합니다!");
			chk_cnt++;
		}
	}
	
	public void captchaImage(String CaptchaKey) {
        String clientId = "8ZR6IilZ2vS3zY0NRsvU";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "OAFp6DVRwg";//애플리케이션 클라이언트 시크릿값";
        try {
            String key = CaptchaKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
            String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                captchaImg = Long.valueOf(new Date().getTime()).toString();
                // 파일 저장위치 변경해보자 (d:/D_Other/복사본_Tulips.jpg)
                // join패키지에 넣자 일단.
                f = new File("captchaImg.jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
		Image captcha = new Image("file:"+f.getAbsolutePath());
		img_auto.setImage(captcha);
	}
	
	public String captchaKey() {
        String clientId = "8ZR6IilZ2vS3zY0NRsvU";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "OAFp6DVRwg";//애플리케이션 클라이언트 시크릿값";
        String result = "";
        try {
            String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            result = response.toString().split("\"")[3];
        } catch (Exception e) {
            System.out.println(e);
        }

		return result;
	}
	public void imgRefresh() {
		captchaKey = captchaKey();
		captchaImage(captchaKey);
	}
	
	public String captchaResult(String CaptchaKey, String input) {
        String clientId = "8ZR6IilZ2vS3zY0NRsvU";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "OAFp6DVRwg";//애플리케이션 클라이언트 시크릿값";
        String result = "";
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String key = CaptchaKey; // 캡차 키 발급시 받은 키값
            String value = input; // 사용자가 입력한 캡차 이미지 글자값
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            result = response.toString().substring(10, 15);
        } catch (Exception e) {
            System.out.println(e);
        }
		return result;
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
		errAlert.setTitle("알림");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}



	
	
	

}
