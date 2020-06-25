package kr.or.ddit.mp.view.review;

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
import kr.or.ddit.mp.service.review.IReviewService;
import kr.or.ddit.mp.vo.review.ReviewVO;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

public class ReviewAdminController implements Initializable{

	@FXML AnchorPane ap_home;
	@FXML AnchorPane an_in;
	@FXML TableView<ReviewVO> tb_adminReview;
	@FXML TableColumn<ReviewVO, String> col_reWriter;
	@FXML TableColumn<ReviewVO, String> col_reTitle;
	@FXML TableColumn<ReviewVO, String> col_reCont;
	@FXML TableColumn<ReviewVO, String> col_reStore;
	@FXML TableColumn<ReviewVO, String> col_reDown;
	@FXML TableColumn<ReviewVO, String> col_reDate;
	@FXML Pagination page_adminReview;
	@FXML JFXButton btn_del;
	@FXML JFXButton btn_show;
	//getClass().getResource("/tableview.css").toString()
	//리뷰 서비스 
	private Registry reg;
	private IReviewService irs;
	
	//리뷰 번호
	int cnt_de     = 0;
	String real_no = null;
	
	//리뷰 평점
	String sel_re_score = null;
	
	//리뷰 삭제할 정보~!
	ReviewVO vo = new ReviewVO(); 
	
	//페이지네이션
	private int from, to, itemsForPage;
	private ObservableList<ReviewVO> allTableData, currentPageData;
	
	//글 정보를 담고 있는 리스트
	private ArrayList<ReviewVO> list = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_adminReview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		tb_adminReview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			irs = (IReviewService) reg.lookup("review");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		col_reWriter.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		col_reTitle.setCellValueFactory(new PropertyValueFactory<>("re_title"));
		col_reCont.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_reStore.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_reDown.setCellValueFactory(new PropertyValueFactory<>("re_down"));
		col_reDate.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		
		allTableData = FXCollections.observableArrayList();
		
		try {
			list = (ArrayList<ReviewVO>) irs.adminReview();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//테스트~!
		System.out.println(list.get(0).getRe_no()+"-->번호");
		
		allTableData.setAll(list);
		
		tb_adminReview.setItems(allTableData);
		
		//페이지네이션----------------------------------------------------
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_adminReview.setPageCount(totPageCount);	
			
				page_adminReview.setPageFactory(this::createPage);
			
		System.out.println("페이지 테스트1");
		
		/**
		 * 리뷰 확인하기
		*/
		btn_show.setOnAction(event1->{
			
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(tb_adminReview.getScene().getWindow());
			dialog.setTitle("리뷰 상세보기");
			
			System.out.println("point 2");
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("Review_adminDetail.fxml"));
	           System.out.println("point 3");
	           
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			AnchorPane anc_out 		= (AnchorPane) parent.lookup("#anc_out"); 
			AnchorPane anc_in 		= (AnchorPane) parent.lookup("#anc_in"); 
			
			Label lab_title			= (Label) parent.lookup("#lab_title"); //글제목
			Label lab_id			= (Label) parent.lookup("#lab_id"); //작성자
			ImageView img_up		= (ImageView) parent.lookup("#img_up"); 
			Label lab_up 			= (Label) parent.lookup("#lab_up"); 
			ImageView img_down 		= (ImageView) parent.lookup("#img_down"); 
			Label lab_down			= (Label) parent.lookup("#lab_down"); //신고수
			JFXTextField txtf_cont  = (JFXTextField) parent.lookup("#txtf_cont"); //글내용

			ImageView img_score 	= (ImageView) parent.lookup("#img_score"); 
			ImageView img_rePhoto 	= (ImageView) parent.lookup("#img_rePhoto"); 
			Label lab_date 			= (Label) parent.lookup("#lab_date"); 
			JFXButton btn_in_del 	= (JFXButton) parent.lookup("#btn_in_del"); 
			JFXButton btn_in_ok		= (JFXButton) parent.lookup("#btn_in_ok"); 
			
			System.out.println(tb_adminReview.getSelectionModel().getSelectedItem().getRe_title());
			lab_title.setText(tb_adminReview.getSelectionModel().getSelectedItem().getRe_title());
			lab_id.setText(tb_adminReview.getSelectionModel().getSelectedItem().getMem_id());
			lab_down.setText(tb_adminReview.getSelectionModel().getSelectedItem().getRe_down());
			txtf_cont.setText(tb_adminReview.getSelectionModel().getSelectedItem().getRe_cont());
			lab_date.setText(tb_adminReview.getSelectionModel().getSelectedItem().getRe_date());
			
			for(int i=0; i<list.size(); i++) {
    			if(tb_adminReview.getSelectionModel().getSelectedItem().getCom_name().equals(list.get(i).getCom_name())) {
    				sel_re_score = list.get(i).getRe_score(); //선택한 글의 실제 번호
    			}
    		}
			System.out.println("선택한 글의 평점 : "+sel_re_score);
			if(sel_re_score.equals("3")) {
        		img_score.setImage(new Image(getClass().getResource("good.png").toString()));
        	}
        	else if(sel_re_score.equals("2")) {
        		img_score.setImage(new Image(getClass().getResource("soso.png").toString()));
        		
        	}else if(sel_re_score.equals("1")) {
        		img_score.setImage(new Image(getClass().getResource("bad.png").toString()));
        	}
			
			btn_in_del.setOnAction(event1_1->{
				
				for(int i=0; i<list.size(); i++) {
	    			if(tb_adminReview.getSelectionModel().getSelectedItem().getCom_name().equals(list.get(i).getCom_name())) {
	    				real_no = list.get(i).getRe_no(); //선택한 글의 실제 번호
	    			}
	    		}
				
				vo.setRe_no(real_no);
				
				try {
					cnt_de = irs.deleteAdminReview(vo);
					
					if(cnt_de > 0) {
	    				infoMsg("성공!", "게시글이 성공적으로 삭제되었습니다!");
	    				dialog.close();
	    				refresh();
	    			}
	        		
	        		if(cnt_de < 0) {
	        			System.out.println("삭제 안됨..");
	        			errMsg("실패!", "오류를 찾아주세요."); 
	        		}
	        		
				} catch (RemoteException e) {
					e.printStackTrace();
					
				}
			});
			
			btn_in_ok.setOnAction(event1_2->{
				
				dialog.close();
			});
			
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);//크기 고정
			dialog.show();
		});
		
		/**
		 * 리뷰 삭제하기
		*/
		btn_del.setOnAction(event2->{
			
			for(int i=0; i<list.size(); i++) {
    			if(tb_adminReview.getSelectionModel().getSelectedItem().getCom_name().equals(list.get(i).getCom_name())) {
    				real_no = list.get(i).getRe_no(); //선택한 글의 실제 번호
    			}
    		}
			
			vo.setRe_no(real_no);
			
			try {
				cnt_de = irs.deleteAdminReview(vo);
				
				if(cnt_de > 0) {
    				infoMsg("성공!", "게시글이 성공적으로 삭제되었습니다!");
    				refresh();
    			}
        		
        		if(cnt_de < 0) {
        			System.out.println("삭제 안됨..");
        			errMsg("실패!", "오류를 찾아주세요."); 
        		}
        		
			} catch (RemoteException e) {
				e.printStackTrace();
				
			}
			
		});
	}

	//페이지네이션에 필요한 메서드 - 1
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_adminReview.setItems(getTableViewData(from, to));
		
		System.out.println("페이지 메서드1");
		
		return tb_adminReview;
	}
	
	//페이지네이션에 필요한 메서드 - 2
	private ObservableList<ReviewVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); 
		int totSize = allTableData.size();
		for (int i = from; i<= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		System.out.println("페이지 메서드2");
		
		return currentPageData;
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
	
	//삭제 후 이전화면에 셋팅해주기
	private void refresh() {
		
		try {
			list = (ArrayList<ReviewVO>) irs.adminReview();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_adminReview.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_adminReview.setPageCount(totPageCount);	
	
				page_adminReview.setPageFactory(this::createPage);
		
	}
}
