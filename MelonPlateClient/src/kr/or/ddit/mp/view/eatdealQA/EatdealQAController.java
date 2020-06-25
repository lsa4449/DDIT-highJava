package kr.or.ddit.mp.view.eatdealQA;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.eatdealQA.IEatdealQAService;
import kr.or.ddit.mp.vo.eatdeal.EatqaVO;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.reservation.ReservationVO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class EatdealQAController implements Initializable{

	@FXML AnchorPane ap_home;
	
	@FXML TableView<EatqaVO> tb_QAnotice;
	@FXML TableColumn<EatqaVO, String> col_qa_no;
	@FXML TableColumn<EatqaVO, String> col_qa_title;
	@FXML TableColumn<EatqaVO, String> col_qa_writer;
	@FXML TableColumn<EatqaVO, String> col_qa_date;
	@FXML TableColumn<EatqaVO, String> col_qa_status;
	
	@FXML Pagination page_qa;
	@FXML JFXButton btn_qaWrite;

	//잇딜게시판 서비스 
  	private Registry reg;
  	private IEatdealQAService ies;
	
  	//페이지네이션
  	private int from, to, itemsForPage;
  	private ObservableList<EatqaVO> allTableData, currentPageData;
  	
  	//객체를 담을 vo, list
  	private EatqaVO vo = new EatqaVO();
  	private ArrayList<EatqaVO> list = new ArrayList<>();
  	private ArrayList<EatqaVO> elist = new ArrayList<>();
	
  	//선택한 qa게시글 번호 받아오기
  	String sel_qa_no = null;
  	String sel_eat_no = null;
  	int sel_ind = 0;
  	
  	//실행 오류 확인하기
  	int in_cnt = 0;  
  	
  	
  	//매개변수
  	public static String get_eat_no="";
  	MemberVO mv = LoginSession.session;
  	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_QAnotice.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			ies = (IEatdealQAService) reg.lookup("eatdealqa");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		/**
		 *  잇딜 리스트 출력하기
		 */
		try {
//			list = (ArrayList<EatqaVO>) ies.selectQA("8");
			list = (ArrayList<EatqaVO>) ies.selectQA(get_eat_no);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		col_qa_no.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_qa_title.setCellValueFactory(new PropertyValueFactory<>("eatqa_title"));
		col_qa_writer.setCellValueFactory(new PropertyValueFactory<>("mem_nick"));
		col_qa_date.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		col_qa_status.setCellValueFactory(new PropertyValueFactory<>("eatqa_com_st"));
		
		allTableData = FXCollections.observableArrayList();
		
		System.out.println("table 테스트");
		
		
		//테스트~~~~
//		System.out.println(list.get(0).getEat_no()+"-> 잇딜 번호");
//		System.out.println(list.get(0).getMem_nick()+"-> 잇딜 작성자");
		
		allTableData.setAll(list);
		
		tb_QAnotice.setItems(allTableData);	
		
		tb_QAnotice.setOnMouseClicked(detail->{
			Stage dialog1 = new Stage(StageStyle.UTILITY);
        	
        	dialog1.initModality(Modality.APPLICATION_MODAL);
			dialog1.initOwner(tb_QAnotice.getScene().getWindow());
			dialog1.setTitle("EAT딜 문의글 상세보기");
        	
			System.out.println("point 2");
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("EATDeal_QAdetail.fxml"));
	           System.out.println("EAT딜 문의글 상세보기");
	           
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			AnchorPane anc_out 		= (AnchorPane) parent.lookup("#anc_out");
			AnchorPane anc_in		= (AnchorPane) parent.lookup("#anc_in");
			
			JFXTextField txtf_cont	= (JFXTextField) parent.lookup("#txtf_cont");
			JFXTextField txtf_title	= (JFXTextField) parent.lookup("#txtf_title");
			JFXTextField txtf_ans	= (JFXTextField) parent.lookup("#txtf_ans");
					
			Label lab_date			= (Label) parent.lookup("#lab_date");
			Label lab_nick			= (Label) parent.lookup("#lab_nick");
			Label lab_ans_date		= (Label) parent.lookup("#lab_ans_date");
					
			ImageView img_who		= (ImageView) parent.lookup("#img_who");
			ImageView img_status	= (ImageView) parent.lookup("#img_status");
			
			JFXButton btn_ok		= (JFXButton) parent.lookup("#btn_ok");
			
			try {
				
				EatqaVO in_vo = new EatqaVO();
				/* 잇딜넘버는 나중에 받아오기!!! */
				
				in_vo.setEat_no(get_eat_no);
//				in_vo.setEat_no("4");
				
				//선택된 글 팝업 조건 : qa글번호(eatqa_no), 잇딜번호(eat_no)
				int sel_no = tb_QAnotice.getSelectionModel().getSelectedIndex();
	    		
	    		sel_ind = (page_qa.getCurrentPageIndex() * (itemsForPage)) + sel_no;
				sel_qa_no = list.get(sel_ind).getEatqa_no();
				in_vo.setEatqa_no(sel_qa_no);
				
				elist = (ArrayList<EatqaVO>) ies.selectQAdetail(in_vo);
				
				txtf_cont.setText(elist.get(0).getEatqa_cont());
				txtf_title.setText(elist.get(0).getEatqa_title());
				
				lab_date.setText(elist.get(0).getIn_date());
				lab_nick.setText(elist.get(0).getMem_nick());
				lab_ans_date.setText(elist.get(0).getUp_date());
				
				if(elist.get(0).getEatqa_com()!=null) {
					txtf_ans.setText(elist.get(0).getEatqa_com());
				}
				
				img_who.setImage(new Image(getClass().getResource("who.png").toString()));
				
				if(list.get(0).getEatqa_com()==null) {
					img_status.setImage(new Image(getClass().getResource("cancel.png").toString()));	
				}else {
					img_status.setImage(new Image(getClass().getResource("checked.png").toString()));
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			Scene scene = new Scene(parent);
			
        	System.out.println("팝업 마지막 소스 부분!");
        	
			dialog1.setScene(scene);
			dialog1.setResizable(false); 
			dialog1.show();
			
			btn_ok.setOnAction(e->{
				dialog1.close();
			});
		});
		
		
		btn_qaWrite.setOnAction(event2->{
			
			Stage dialog2 = new Stage(StageStyle.UTILITY);
        	
        	dialog2.initModality(Modality.APPLICATION_MODAL);
			dialog2.initOwner(btn_qaWrite.getScene().getWindow());
			dialog2.setTitle("EAT딜 문의글 작성하기");
        	
			System.out.println("EAT딜 문의글 작성");
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("EATDeal_writer.fxml"));
	           System.out.println("EAT딜 문의글 상세보기");
	           
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			AnchorPane ap_write			= (AnchorPane) parent.lookup("#ap_write");
			
			JFXTextField txtf_w_title	= (JFXTextField) parent.lookup("#txtf_w_title");
			JFXTextArea txta_w_cont		= (JFXTextArea) parent.lookup("#txta_w_cont");
			
			JFXButton btn_w_finish		= (JFXButton) parent.lookup("#btn_w_finish");
			JFXButton btn_w_cancle		= (JFXButton) parent.lookup("#btn_w_cancle");
			
			
			btn_w_finish.setOnAction(event2_1->{
				
				try {
					//입력값 : 잇딜번호(eat_no), 제목(eatqa_title), 내용(eatqa_cont), 회원번호(mem_no) --->잇딜번호 연동, 회원번호 로그인시 연동해야함			
					EatqaVO qvo = new EatqaVO();

//					qvo.setEat_no("4");
					qvo.setEat_no(get_eat_no);
//					qvo.setMem_no("70");
					qvo.setMem_no(mv.getMem_no());
					
					if(txtf_w_title.getText()!=null ) {
						qvo.setEatqa_title(txtf_w_title.getText());
					}else {
						System.out.println("제목 삽입 오류");
						errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
						return;
					}
					
					if(txta_w_cont.getText()!=null) {
						qvo.setEatqa_cont(txta_w_cont.getText());
					}else {
						System.out.println("내용 삽입 오류");
						errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
						return;
					}
					
					in_cnt = ies.insertQA(qvo);
					
					if(in_cnt > 0) {
						infoMsg("성공!", "게시글이 성공적으로 등록되었습니다!");
						dialog2.close();
						refresh();	
						
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
						return;
					}
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});
			
			btn_w_cancle.setOnAction(event2_2->{
				
				dialog2.close();
				
			});
			
			Scene scene = new Scene(parent);
			
			dialog2.setScene(scene);
			dialog2.setResizable(false); 
			dialog2.show();
			
		});
		
		//페이지 새로고침
		refresh();
	}

	//페이지네이션----------------------------------------------------	
	private void setPagination() {

		itemsForPage = 16;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_qa.setPageCount(totPageCount);	
			
				page_qa.setPageFactory(this::createPage);

	}
	
	//페이지네이션에 필요한 메서드 - 1
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_QAnotice.setItems(getTableViewData(from, to));
		
		System.out.println("페이지 메서드1");
		
		return tb_QAnotice;
	}
	
	//페이지네이션에 필요한 메서드 - 2
	private ObservableList<EatqaVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); 
		int totSize = allTableData.size();
		for (int i = from; i<= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		System.out.println("페이지 메서드2");
		
		return currentPageData;
	}
	
	//수정이나, 삭제 후 이전화면에 셋팅해주기
	private void refresh() {
		
		try {
//			list = (ArrayList<EatqaVO>) ies.selectQA("8");
			list = (ArrayList<EatqaVO>) ies.selectQA(get_eat_no);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_QAnotice.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_qa.setPageCount(totPageCount);	
	
				page_qa.setPageFactory(this::createPage);
		
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
}
