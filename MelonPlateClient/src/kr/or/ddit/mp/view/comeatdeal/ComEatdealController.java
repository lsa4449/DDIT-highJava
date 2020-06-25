package kr.or.ddit.mp.view.comeatdeal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.comeatdeal.IComEatdealService;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.member.ComVO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;

public class ComEatdealController implements Initializable{
	@FXML AnchorPane anpane_eat_list;
	@FXML TableView<EatdealVO> tb_eatList;
	@FXML TableColumn<EatdealVO, String> col_eatSta;
	@FXML TableColumn<EatdealVO, String> col_eatName;
	@FXML TableColumn<EatdealVO, String> col_eatPri;
	@FXML TableColumn<EatdealVO, String> col_eatDate;

	@FXML Pagination page_eat;
	
	private int from, to, itemsForPage;
	private ObservableList<EatdealVO> allTableData, currentPageData;
	
	
	private Registry reg;
	private IComEatdealService comEatdeal;
	 
	ArrayList<EatdealVO> list;
	@FXML JFXButton btn_insert;
	@FXML JFXButton btn_delete;
	
	////////////
	ComVO cv = LoginSession.comsession;
	String word = cv.getCom_id();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_eatList.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			comEatdeal = (IComEatdealService) reg.lookup("comEatService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		//--------------------
		col_eatSta.setCellValueFactory(new PropertyValueFactory<>("eat_sta"));
		col_eatName.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		col_eatPri.setCellValueFactory(new PropertyValueFactory<>("eat_price"));
		col_eatDate.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		
		
		//---------

		viewTb();
		
	}
	
	private void viewTb() {
		
		
		try {
			list = (ArrayList<EatdealVO>) comEatdeal.getComEatdealList(word);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String str_sysdate = transFormat.format(new Date());
		
		int strtYear = Integer.parseInt(str_sysdate.substring(0,4));
		int strtMonth = Integer.parseInt(str_sysdate.substring(5,7));
		int strtDay = Integer.parseInt(str_sysdate.substring(8,10));
		System.out.println("strtDay:"+strtDay);
		int endYear = 0;
		int endMonth =0; 
		int endDay =0; 

		int month = 0;
//		int to_day = 0;
//		to_day= 30 -strtDay;
//		int from_day = 0;
		Date in_date = new Date();
			
		for (int i = 0; i < list.size(); i++) {
//			try {
//				in_date = transFormat.parse(list.get(i).getIn_date());
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			String str_in_date = list.get(i).getIn_date();
			System.out.println( list.size());
			System.out.println(str_in_date);
			if(str_in_date == null && list.get(i).getEat_del().equals("N")) {
				list.get(i).setEat_sta("승인대기중");
			} else if(str_in_date == null && list.get(i).getEat_del().equals("B")){
				list.get(i).setEat_sta("승인거절");
			} else if(list.get(i).getEat_del().equals("Y")){
		
				list.get(i).setEat_sta("삭제대기중");
			}else {
				endYear = Integer.parseInt(str_in_date.substring(0,4));
				endMonth = Integer.parseInt(str_in_date.substring(5,7));
				endDay = Integer.parseInt(str_in_date.substring(8,10));
				System.out.println("endDay:"+endDay);
				System.out.println("endMonth:"+endMonth);
				
				month= (endYear - strtYear)* 12 + (endMonth - strtMonth);
//				from_day= 30 - endDay;
				if((month< 1 && (strtDay != endDay)) || (month< 1 && (strtDay == endDay))) {
					list.get(i).setEat_sta("진행중");
				}else {
					list.get(i).setEat_sta("종료");
				}
			}
		}
		
		allTableData = FXCollections.observableArrayList();
		
		
		allTableData.setAll(list);
		itemsForPage = 5;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_eat.setPageCount(totPageCount);	
		page_eat.setPageFactory(this::createPage);
		
	}
	
	
	
	private ObservableList<EatdealVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); 
		int totSize = allTableData.size();
		for (int i = from; i<= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		return currentPageData;
	}
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_eatList.setItems(getTableViewData(from, to));
		
		return tb_eatList;
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


	@FXML public void Clickadd(ActionEvent event) {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_insert.getScene().getWindow());
		dialog.setTitle("새로운 EAT딜 신청");
		
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("com_eat_popup.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
        
		JFXTextField txtf_name 	= (JFXTextField) parent.lookup("#txtf_name");
		JFXTextField txtf_total = (JFXTextField) parent.lookup("#txtf_total");
		JFXTextArea txta_menu 	= (JFXTextArea) parent.lookup("#txta_menu");
		JFXTextField txtf_won 	= (JFXTextField) parent.lookup("#txtf_won");
		JFXTextField txtf_pic 	= (JFXTextField) parent.lookup("#txtf_pic");
		Text txt_title 			= (Text) parent.lookup("#txt_title");
        
        JFXButton btn_suc 	= (JFXButton) parent.lookup("#btn_suc");
        JFXButton btn_can 	= (JFXButton) parent.lookup("#btn_can");
        JFXButton btn_find 	= (JFXButton) parent.lookup("#btn_find");
        
        
        
        btn_find.setOnAction(finding->{
        	FileInputStream fin = null;
    		FileOutputStream fout = null;
    		
    		FileChooser chooser = new FileChooser();
    		
    		// 확장자 별로 파일 구분하는 필터 등록하기
    		chooser.getExtensionFilters().addAll(
    				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    		
    		// Dialog창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고
    		// '취소'버튼을 누르면 null이 반환된다.
    		File selectFile = chooser.showOpenDialog(dialog);
    		String set_file = ".\\img\\eatdealcom\\" + selectFile.getName();
    		if(selectFile != null) {
    			// 이 영역에서 파일내용을 읽어오는 작업을 수행한다.
    			txtf_pic.setText(set_file);
    		}
    		
    		
    		try {
    			
    			fin = new FileInputStream(selectFile.getAbsolutePath());
    			fout = new FileOutputStream(set_file);
//    			fout = new FileOutputStream("D:\\A_TeachingMaterial\\3.HignJava\\workspace\\04080100.zip_expanded\\MelonJAVAclientMe\\img\\comlmg\\" + selectFile.getName());
    			
    			int c;
    			
    			while((c = fin.read()) != -1) {
    				fout.write(c);
    			}
    			
    			fin.close();
    			fout.close();
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
        });
        
        
        btn_can.setOnAction(cancle ->{
        	dialog.close();
        });
         
        btn_suc.setOnAction(complete ->{
        	
        	Pattern num_reg = Pattern.compile("[0-9]+");
        	
        	if((txtf_name.getText().equals("")||txtf_name.getText()==null) ||
        		(txtf_total.getText().equals(" ")||txtf_total.getText()==null) ||
        		(txta_menu.getText().equals(" ")||txta_menu.getText()==null) ||
        		(txtf_pic.getText().equals(" ")||txtf_pic.getText()==null) ||
        		(txtf_won.getText().equals(" ")||txtf_won.getText()==null)
        		
        		) {
        		errMsg("경고!", "빈 항목이 있는지 확인해보세요");
				return;
        	}
        	
        	Matcher num_reg_ma1 = num_reg.matcher(txtf_total.getText());
        	Matcher num_reg_ma2 = num_reg.matcher(txtf_won.getText());
        	
        	if( num_reg_ma1.matches() == false || num_reg_ma2.matches() == false) {
        		errMsg("경고!", "가격과 수량은 숫자만 입력가능합니다.");
        		return;
        	}
        	
        	EatdealVO eatvo = new EatdealVO();
        	eatvo.setCom_id(word);
        	eatvo.setEat_name(txtf_name.getText());
        	eatvo.setEat_cont1(txta_menu.getText());
        	eatvo.setEat_cont2(txtf_pic.getText());
        	eatvo.setEat_price(txtf_won.getText());
        	eatvo.setEat_qty(txtf_total.getText());
        	int cnt = 0;
        	try {
				cnt = comEatdeal.insertNewNoEatdeal(eatvo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
        	
        	if(cnt > 0) {
    			infoMsg("신청 결과", cnt + "개의 EAT딜이 신청완료되었습니다.");
    			
    		}else {
    			errMsg("신청 결과", "EAT딜 신청에 오류가 있습니다. 항목을 확인하세요.");
    			return;
    		}
        	dialog.close();
        	viewTb();
        	
        });
        
        
        
        
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); // 크기 고정 true는 가변
        dialog.show();
		
		
		
		
	}


	@FXML public void Clickdel(ActionEvent event) throws RemoteException {
		
		int select_ind = tb_eatList.getSelectionModel().getSelectedIndex();
		if( select_ind <= -1 ) {
			errMsg("오류!", "삭제신청 할 EAT딜을 클릭하세요!");
			return;
		}
		
		EatdealVO temEatvo = list.get(select_ind);
		System.out.println("temEatvo.getEat_no(): "  + temEatvo.getEat_no());
		int cnt = comEatdeal.deleteEatdeal(temEatvo);
		if(cnt > 0) {
			infoMsg("삭제 신청결과", cnt + "개의 EAT딜이 삭제신청되었습니다.");
			viewTb();
		}else {
			errMsg("삭제 신청결과", "삭제신청된 EAT딜이 없습니다.");
			viewTb();
		}
		
		
		
	}
}
