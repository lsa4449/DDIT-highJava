package kr.or.ddit.mp.view.recogeatdeal;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.service.recogeatdeal.IRecogeatdealService;
import kr.or.ddit.mp.vo.buylist.RequestVO;
import kr.or.ddit.mp.vo.recogeatdeal.EatdealVO;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class RecogMainController implements Initializable {
	
	@FXML TableView<EatdealVO> tb_recog;
	@FXML TableColumn<EatdealVO, String> col_comname;
	@FXML TableColumn<EatdealVO, String> col_eatname;
	@FXML TableColumn<EatdealVO, String> col_comid;
	@FXML TableColumn<EatdealVO, String> col_date;
	@FXML TableColumn<EatdealVO, String> col_eatapp;
	@FXML Label lab_recog;
	@FXML Button btn_info;

	
	private Registry reg;
	private IRecogeatdealService irecog;
	
	private int from, to, itemsForPage;
	private ObservableList<EatdealVO> allTableData;
	
	ArrayList<EatdealVO> list = new ArrayList<>();
	EatdealVO eat = new EatdealVO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tb_recog.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry("localhost", 8429);
			// 설정한 서버를 찾는 메소드 lookup
			
			irecog = (IRecogeatdealService) reg.lookup("recog");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		col_comname.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_eatname.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		col_comid.setCellValueFactory(new PropertyValueFactory<>("com_id"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		col_eatapp.setCellValueFactory(new PropertyValueFactory<>("eat_sta"));
		
		
		
		
		allTableData = FXCollections.observableArrayList();
		
		
		try {
			list = (ArrayList<EatdealVO>) irecog.getAllEatList(); // 전체 정보 가져온거다
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getEat_approve().equals("Y")) {
				list.get(i).setEat_sta("승인");
			}else if(list.get(i).getEat_approve().equals("N")) {
				list.get(i).setEat_sta("대기중");
			}else if(list.get(i).getEat_approve().equals("B")) {
				list.get(i).setEat_sta("반려");
			}
			
		}
		
		allTableData.setAll(list);
		tb_recog.setItems(allTableData);
		
		btn_info.setOnAction(e->{
			if(tb_recog.getSelectionModel().isEmpty()) {
				errMsg("작업 오류", "자료를 선택한 후 클릭하세요.");
		        return;
			}
			
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("recogeatpop.fxml"));
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			Button btn_ok = (Button) parent.lookup("#btn_ok"); 
			Button btn_no = (Button) parent.lookup("#btn_no"); 
			Button btn_back = (Button) parent.lookup("#btn_back"); 
    		TextField txtf_eatname = (TextField) parent.lookup("#txtf_eatname");
    		TextField txtf_qty = (TextField) parent.lookup("#txtf_qty");
    		TextField txtf_price = (TextField) parent.lookup("#txtf_price");
    		TextField txtf_img = (TextField) parent.lookup("#txtf_img");
    		TextArea txta_menu = (TextArea) parent.lookup("#txta_menu");
    		
    		eat = tb_recog.getSelectionModel().getSelectedItem(); // 선택된 데이터 정보 가져옴
    		txtf_eatname.setText(eat.getEat_name());
    		txtf_qty.setText(eat.getEat_qty());
    		txtf_price.setText(eat.getEat_price());
    		txtf_img.setText(eat.getEat_cont2());
    		txta_menu.setText(eat.getEat_cont1());
    		
    		txtf_eatname.setEditable(false);
    		txtf_qty.setEditable(false);
    		txtf_price.setEditable(false);
    		txtf_img.setEditable(false);
    		txta_menu.setEditable(false);
    		
    		btn_ok.setOnAction(e1 ->{
    			int cnt = 0;
    			EatdealVO evo = new EatdealVO();
        		evo.setEat_no(eat.getEat_no());
    			try {
    				cnt = irecog.updateOnY(evo);
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
    			if(cnt > 0 ) {
    				infoMsg("작업결과", "승인되었습니다.");
    				
    				try {
    					list = (ArrayList<EatdealVO>) irecog.getAllEatList(); // 전체 정보 가져온거다
    				} catch (RemoteException e2) {
    					e2.printStackTrace();
    				}

    				for(int i = 0; i < list.size(); i++) {
    					if (list.get(i).getEat_approve().equals("Y")) {
    						list.get(i).setEat_sta("승인");
    					}else if(list.get(i).getEat_approve().equals("N")) {
    						list.get(i).setEat_sta("대기중");
    					}else if(list.get(i).getEat_approve().equals("B")) {
    						list.get(i).setEat_sta("반려");
    					}
    					
    				}
    				
    				allTableData.setAll(list);
    				tb_recog.setItems(allTableData);
    				
    			} else {
    				errMsg("작업실패", "작업이 실패하였습니다.");
    			}
    			dialog.close();
    		});
    		
    		btn_no.setOnAction(e2 ->{
    			int cnt = 0;
    			EatdealVO evo = new EatdealVO();
        		evo.setEat_no(eat.getEat_no());
    			try {
    				cnt = irecog.updateOnB(evo);
				} catch (RemoteException e3) {
					e3.printStackTrace();
				}
    			if(cnt > 0 ) {
    				infoMsg("작업결과", "반려되었습니다.");
    				
    				try {
    					list = (ArrayList<EatdealVO>) irecog.getAllEatList(); // 전체 정보 가져온거다
    				} catch (RemoteException e3) {
    					e3.printStackTrace();
    				}

    				for(int i = 0; i < list.size(); i++) {
    					if (list.get(i).getEat_approve().equals("Y")) {
    						list.get(i).setEat_sta("승인");
    					}else if(list.get(i).getEat_approve().equals("N")) {
    						list.get(i).setEat_sta("대기중");
    					}else if(list.get(i).getEat_approve().equals("B")) {
    						list.get(i).setEat_sta("반려");
    					}
    					
    				}
    				
    				allTableData.setAll(list);
    				tb_recog.setItems(allTableData);
    				
    			} else {
    				errMsg("작업실패", "작업이 실패하였습니다.");
    			}
    			dialog.close();
    		});
    		
    		btn_back.setOnAction(event -> dialog.close()); // 돌아가기 버튼
    		
    		
			Scene scene = new Scene(parent);
	        
	        dialog.setScene(scene);
	        dialog.setResizable(false);
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
