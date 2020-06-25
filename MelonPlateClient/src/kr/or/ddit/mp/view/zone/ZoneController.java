package kr.or.ddit.mp.view.zone;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.model.DataModel;
import kr.or.ddit.mp.service.zone.IZoneService;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.mypage.ZiptbVO;
import kr.or.ddit.mp.vo.mypage.ZoneVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class ZoneController implements Initializable{

	@FXML AnchorPane ap_write;
	@FXML JFXTextField txtf_research;
	
	@FXML JFXButton btn_set;
	@FXML JFXButton btn_research;
	
	@FXML TableView<ZiptbVO> tb_zone;
	@FXML TableColumn<ZiptbVO, String> col_sido;
	@FXML TableColumn<ZiptbVO, String> col_gu;
	@FXML TableColumn<ZiptbVO, String> col_dong;
	
	@FXML ImageView img_zone1;
	@FXML ImageView img_zone2;
	@FXML ImageView img_zone3;
	
	@FXML JFXButton btn_minus;
	@FXML JFXButton btn_add;
	
	@FXML Text txt_1;
	@FXML Text txt_2;
	@FXML Text txt_3;
	
	//로그인유저의 관심지역 설정하기
	MemberVO mv = LoginSession.session;
	String user = "";
	
	//관심지역 설정하기
	private Registry reg;
	private IZoneService izs;
	
	//관심지역 인서트 확인하기
	private int ch_1 = 0;
	private int ch_2 = 0;
	private int ch_3 = 0;
	
	//관심지역 변수
	private String zone1 = null;
	private String zone2 = null;
	private String zone3 = null;
	
	String[] dongs = null;

	//vo선언
	ZoneVO vo = new ZoneVO();
	
	//ziptb 테이블 정보를 담고 있는 컬럼
	private ArrayList<ZiptbVO> list = new ArrayList<>();
	
	private ObservableList<ZiptbVO> allTableData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tb_zone.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			izs = (IZoneService) reg.lookup("zone");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
//		refresh();
		
		ArrayList<ZoneVO> zlist = new ArrayList<>();
		
		user = mv.getMem_no();
		vo.setMem_no(user);
		
		try {
			zlist = (ArrayList<ZoneVO>) izs.selectZoneChoice(vo);
			
			if(zlist.size()!=0 &&
			   zlist.get(0).getZone1()!=null &&
			   zlist.get(0).getZone2()!=null &&
			   zlist.get(0).getZone3()!=null) {
				
				txt_1.setText(zlist.get(0).getZone1());
				txt_2.setText(zlist.get(0).getZone2());
				txt_3.setText(zlist.get(0).getZone3());
				
				img_zone1.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				img_zone2.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				img_zone3.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				
			}else if(zlist.size()!=0 &&
					 zlist.get(0).getZone1()!=null &&
					 zlist.get(0).getZone2()!=null) {
				
				txt_1.setText(zlist.get(0).getZone1());
				txt_2.setText(zlist.get(0).getZone2());
				
				img_zone1.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				img_zone2.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				img_zone3.setImage(new Image(getClass().getResource("placeholder.png").toString()));
				
			}else if(zlist.size()!=0 && zlist.get(0).getZone1()!=null) {
				txt_1.setText(zlist.get(0).getZone1());
				
				img_zone1.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
				img_zone2.setImage(new Image(getClass().getResource("placeholder.png").toString()));
				img_zone3.setImage(new Image(getClass().getResource("placeholder.png").toString()));
			}
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		
		
		txtf_research.setPromptText("동으로 입력해주세요.");
		
		btn_research.setOnAction(event->{
			
			col_sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
			col_gu.setCellValueFactory(new PropertyValueFactory<>("gugun"));
			col_dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
			
			allTableData = FXCollections.observableArrayList();
			
			/**
			 *  주소(동으로) 출력하기
			 */
			try {
				list = (ArrayList<ZiptbVO>) izs.selectZone(txtf_research.getText());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			allTableData.setAll(list);
			
			tb_zone.setItems(allTableData);
			
			
		});
		
		btn_add.setOnAction(event1->{
			
			//관심지역1이 설정되어있지 않는 경우
			user = mv.getMem_no();
			vo.setMem_no(user);
			
			if(txt_1.getText().equals("관심지역1")) {
				
				dongs = tb_zone.getSelectionModel().getSelectedItem().getDong().split("\\s");
				
				zone1 = dongs[0];
				System.out.println(zone1);
				
				txt_1.setText(zone1);
				
				try {
					vo.setZone1(zone1);
					ch_1 =  izs.insertZone1(vo);
					
					if(ch_1 > 0) {
						infoMsg("성공!", "관심지역1이 설정되었습니다!");
						img_zone1.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
						
					}else {
						errMsg("실패!", "다시 한번 더 확인해주세요.1");
						return;
					}
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			//관심지역1이 설정되어있고, 관심지역2가 설정되어있지 않을 경우
			}else if(txt_1.getText()!=null) {
				if(txt_2.getText().equals("관심지역2")) {
					dongs = tb_zone.getSelectionModel().getSelectedItem().getDong().split("\\s");
					
					String zone2 = dongs[0];
					
					System.out.println(dongs[0]);
					
					txt_2.setText(zone2);
					
					try {
						vo.setZone2(zone2);
						
						ch_2 =  izs.updateZone2(vo);
						
						if(ch_2 > 0) {
							infoMsg("성공!", "관심지역2가 설정되었습니다!");
							img_zone2.setImage(new Image(getClass().getResource("placeholderP.png").toString()));

						}else {
							errMsg("실패!", "다시 한번 더 확인해주세요.2");
							return;
						}
						
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
				//관심지역2가 설정되어있을 경우, 관심지역 3이 설정되어있지 않을 경우
				}else if(txt_2.getText()!=null) {
					if(txt_3.getText().equals("관심지역3")) {
						dongs = tb_zone.getSelectionModel().getSelectedItem().getDong().split("\\s");
						
						String zone3 = dongs[0];
						
						txt_3.setText(zone3);
					
						try {
							
							vo.setZone3(zone3);
							
							ch_3 =  izs.updateZone3(vo);
							
							if(ch_3 > 0) {
								infoMsg("성공!", "관심지역3이 설정되었습니다!");
								img_zone3.setImage(new Image(getClass().getResource("placeholderP.png").toString()));
								
							}else {
								errMsg("실패!", "다시 한번 더 확인해주세요.3");
								return;
							}
							
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else if(txt_3.getText()!=null) {
						errMsg("실패!", "관심지역은 3개만 등록가능합니다.");
						return;
					}
				}
				
			}
			
			
		});
		
		btn_minus.setOnAction(event2->{
			
			ZoneVO in_vo = new ZoneVO();
			
			user = mv.getMem_no();
			in_vo.setMem_no(user);
			
			int ch_de3 = 0;
			int ch_de2 = 0;
			int ch_de1 = 0;
			
			
			if(!txt_3.getText().equals("관심지역3") &&
			   !txt_2.getText().equals("관심지역2") &&
			   !txt_1.getText().equals("관심지역1")		) {
				
				try {
					ch_de3 = izs.deleteZone3(in_vo);
					
					if(ch_de3 > 0) {
						System.out.println("관심지역3 삭제");
						txt_3.setText("관심지역3");
						img_zone3.setImage(new Image(getClass().getResource("placeholder.png").toString()));
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
					}
					
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
			}else if(!txt_2.getText().equals("관심지역2") &&
					 !txt_1.getText().equals("관심지역1")   ) {
				
				try {
					ch_de2 = izs.deleteZone2(in_vo);
					
					if(ch_de2 > 0) {
						System.out.println("관심지역2 삭제");
						txt_2.setText("관심지역2");
						img_zone2.setImage(new Image(getClass().getResource("placeholder.png").toString()));
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
					}
					
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
			}else if(!txt_1.getText().equals("관심지역1")) {
				
				try {
					ch_de1 = izs.deleteZone1(in_vo);
					
					if(ch_de1 > 0) {
						System.out.println("관심지역1 삭제");
						txt_1.setText("관심지역1");
						img_zone1.setImage(new Image(getClass().getResource("placeholder.png").toString()));
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
					}
					
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
			}
			
		});
		
		btn_set.setOnAction(event4->{
			
			if(txt_1.getText().equals("관심지역1")) {
				errMsg("실패!", "관심지역을 설정해주세요!");
				return;
			}
			
			Stage dialog = (Stage) btn_set.getScene().getWindow();
			
			if(DataModel.isFirstZone) { // 처음 
				
				/**
				 * 메인화면 진입
				 */
	
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/mp/main/top_member.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(parent);
				Stage main1 = new Stage();
				main1.setTitle("EAT, SHARE, BE HAPPY! 멜론플레이트 :) ");
				main1.setScene(scene);
				main1.show();
				dialog.close();
				DataModel.isFirstZone = false;
			}else {
				dialog.close();
			}
		
			
		});
		
	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	private void refresh() {
		
		ArrayList<ZoneVO> zlist = new ArrayList<>();
		
		user = mv.getMem_no();
		vo.setMem_no(user);
		
		try {
			zlist = (ArrayList<ZoneVO>) izs.selectZoneChoice(vo);
			
			if(zlist.size()!=0 && zlist.get(0).getZone1()!=null) {
				txt_1.setText(zlist.get(0).getZone1());
				
			}else if(zlist.size()!=0 && zlist.get(0).getZone2()!=null) {
				txt_2.setText(zlist.get(0).getZone2());
				
			}else if(zlist.size()!=0 && zlist.get(0).getZone3()!=null) {
				txt_3.setText(zlist.get(0).getZone3());
			}
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
