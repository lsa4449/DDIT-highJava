package kr.or.ddit.mp.view.qnacomeatdeal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.comeatdeal.IComEatdealService;
import kr.or.ddit.mp.service.qnacomeatdeal.IQnacomEatdealService;
import kr.or.ddit.mp.vo.eatdeal.EatdealVO;
import kr.or.ddit.mp.vo.eatdeal.EatqaVO;
import kr.or.ddit.mp.vo.member.ComVO;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class QnaComeatdealController implements Initializable{

	@FXML AnchorPane anpane_eat_list;
	@FXML TableView<EatqaVO> tb_eatList;
	@FXML TableColumn<EatqaVO, String> col_qnaSta;
	@FXML TableColumn<EatqaVO, String> col_qnaTitle;
	@FXML TableColumn<EatqaVO, String> col_eatName;
	@FXML TableColumn<EatqaVO, String> col_qDate;
	@FXML TableColumn<EatqaVO, String> col_aDate;
	@FXML Pagination page_qna;
	@FXML JFXButton btn_all;
	@FXML JFXButton btn_noans;
	@FXML JFXButton btn_ans;
	@FXML JFXButton btn_view;
	
	private int from, to, itemsForPage;
	private ObservableList<EatqaVO> allTableData, currentPageData;
	private Registry reg;
	private IQnacomEatdealService qnaSer;
	
	ArrayList<EatqaVO> list;
	ComVO cv = LoginSession.comsession;
//	String com_id = "366-45-90024";
	String com_id = cv.getCom_id();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_eatList.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		///////////////////rmi
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			qnaSer = (IQnacomEatdealService) reg.lookup("qnaComService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		/////////////////////
		col_qnaSta.setCellValueFactory(new PropertyValueFactory<>("eatqa_str_st"));
		col_qnaTitle.setCellValueFactory(new PropertyValueFactory<>("eatqa_title"));
		col_eatName.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		col_qDate.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		col_aDate.setCellValueFactory(new PropertyValueFactory<>("up_date"));
		/////////////////////
		viewTb();
	}

	private void viewTb() {
		try {
			list = (ArrayList<EatqaVO>) qnaSer.getAllQnaComeatdealList(com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			String ans_sta = list.get(i).getEatqa_com_st();
			if(ans_sta.equals("N")){
				list.get(i).setEatqa_str_st("미답변");
			}else {
				list.get(i).setEatqa_str_st("답변완료");
			}
			
		}
		
		allTableData = FXCollections.observableArrayList();
		
		allTableData.setAll(list);
		itemsForPage = 5;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_qna.setPageCount(totPageCount);	
		page_qna.setPageFactory(this::createPage);
		
		btn_ans.setDisable(true);
		btn_view.setDisable(true);
		
		tb_eatList.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				int select_ind = tb_eatList.getSelectionModel().getSelectedIndex();
				System.out.println(select_ind);
				if( select_ind != -1 && list.get(select_ind).getEatqa_com_st().equals("N")) {
					btn_ans.setDisable(false);
					btn_view.setDisable(true);
				}else if(select_ind != -1 && list.get(select_ind).getEatqa_com_st().equals("Y")) {
					btn_ans.setDisable(true);
					btn_view.setDisable(false);
				}else if(tb_eatList.getSelectionModel().getSelectedItems() == null ){
					btn_ans.setDisable(true);
					btn_view.setDisable(true);
				}
//				
			}
		});
		
		
	}
	private void viewNotTb() {
		try {
			list = (ArrayList<EatqaVO>) qnaSer.getNotQnaComeatdealList(com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			String ans_sta = list.get(i).getEatqa_com_st();
			if(ans_sta.equals("N")){
				list.get(i).setEatqa_str_st("미답변");
			}else {
				list.get(i).setEatqa_str_st("답변완료");
			}
			
		}
		
		allTableData = FXCollections.observableArrayList();
		
		allTableData.setAll(list);
		itemsForPage = 5;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
						: allTableData.size()/itemsForPage + 1; 
				
		page_qna.setPageCount(totPageCount);	
		page_qna.setPageFactory(this::createPage);
				
		btn_ans.setDisable(true);
		btn_view.setDisable(true);
		
		tb_eatList.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				int select_ind = tb_eatList.getSelectionModel().getSelectedIndex();
				System.out.println(select_ind);
				if( select_ind != -1 && list.get(select_ind).getEatqa_com_st().equals("N")) {
					btn_ans.setDisable(false);
					btn_view.setDisable(true);
				}else if(select_ind != -1 && list.get(select_ind).getEatqa_com_st().equals("Y")) {
					btn_ans.setDisable(true);
					btn_view.setDisable(false);
				}else if(tb_eatList.getSelectionModel().getSelectedItems() == null ){
					btn_ans.setDisable(true);
					btn_view.setDisable(true);
				}
//				
			}
		});
				
	}

	@FXML public void Clickall(ActionEvent event) {
		viewTb();
	}

	@FXML public void Clicknot(ActionEvent event) {
		viewNotTb();
	}

	@FXML public void Clickanswer(ActionEvent event) {
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_ans.getScene().getWindow());
		dialog.setTitle("EAT딜 답변하기");
		
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("com_eat_qna_popup.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
        
		JFXTextArea txta_que 		= (JFXTextArea) parent.lookup("#txta_que");
		JFXTextArea txta_ans 		= (JFXTextArea) parent.lookup("#txta_ans");
		JFXTextField txtf_title 	= (JFXTextField) parent.lookup("#txtf_title");
        
        JFXButton btn_suc 	= (JFXButton) parent.lookup("#btn_suc");
        JFXButton btn_can 	= (JFXButton) parent.lookup("#btn_can");
        int select_i = tb_eatList.getSelectionModel().getSelectedIndex();
        
        txtf_title.setText(list.get(select_i).getEatqa_title());
        txta_que.setText(list.get(select_i).getEatqa_cont());
        
        txtf_title.setEditable(false);
        txta_que.setEditable(false);
       
        btn_can.setOnAction(cancle ->{
        	dialog.close();
        });
         
        btn_suc.setOnAction(complete ->{
        	
        	int select_ind = tb_eatList.getSelectionModel().getSelectedIndex();
        	System.out.println(list.get(select_ind).getEatqa_no());
        	System.out.println(txta_ans.getText());
        	
        	if((txta_ans.getText().equals("")||txta_ans.getText()==null)) {
        		errMsg("경고!", "답변을 작성해 주세요.");
				return;
        	}
        	
        	EatqaVO qavo = new EatqaVO();
        	qavo.setEatqa_no(list.get(select_ind).getEatqa_no());
        	qavo.setEatqa_com(txta_ans.getText());
        	int cnt = 0;
        	try {
				cnt = qnaSer.ansQna(qavo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
        	
        	if(cnt > 0) {
    			infoMsg("답변 결과", "답변이 등록되었습니다 :) ");
    			
    		}else {
    			errMsg("답변 결과", "답변 등록에 오류가 있습니다 :( ");
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
	
	private ObservableList<EatqaVO> getTableViewData(int from, int to){
		
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

	@FXML public void Clickview(ActionEvent event) {
		
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_ans.getScene().getWindow());
		dialog.setTitle("EAT딜 답변하기");
		
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("com_eat_qna_popup.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
        
		JFXTextArea txta_que 		= (JFXTextArea) parent.lookup("#txta_que");
		JFXTextArea txta_ans 		= (JFXTextArea) parent.lookup("#txta_ans");
		JFXTextField txtf_title 	= (JFXTextField) parent.lookup("#txtf_title");
        
        JFXButton btn_suc 	= (JFXButton) parent.lookup("#btn_suc");
        JFXButton btn_can 	= (JFXButton) parent.lookup("#btn_can");
        int select_i = tb_eatList.getSelectionModel().getSelectedIndex();
        
        txtf_title.setText(list.get(select_i).getEatqa_title());
        txta_que.setText(list.get(select_i).getEatqa_cont());
        txta_ans.setText(list.get(select_i).getEatqa_com());
        
        txtf_title.setEditable(false);
        txta_que.setEditable(false);
        txta_ans.setEditable(false);
       
        btn_suc.setVisible(false);
        btn_can.setText("창 닫기");
        btn_can.setLayoutX(230);
        
        btn_can.setOnAction(cancle ->{
        	dialog.close();
        	
        });
         
       
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(false); // 크기 고정 true는 가변
        dialog.show();
		
		
	}

}
