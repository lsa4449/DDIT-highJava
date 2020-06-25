package kr.or.ddit.mp.view.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.review.IReviewService;

import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.review.ReviewVO;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.TableColumn;

public class ReviewMyController implements Initializable{

	@FXML AnchorPane ap_home;
	@FXML AnchorPane an_in;
	
	@FXML TableView<ReviewVO> tb_myReview;
	@FXML TableColumn<ReviewVO, String> col_reNo;
	@FXML TableColumn<ReviewVO, String> col_reCom;
	@FXML TableColumn<ReviewVO, String> col_reTitle;
	@FXML TableColumn<ReviewVO, String> col_reCont;
	@FXML TableColumn<ReviewVO, String> col_reDate;
	@FXML TableColumn<ReviewVO, String> col_reScore;
	@FXML TableColumn<ReviewVO, String> col_reUp;
	@FXML Pagination page_myReview;
	
	@FXML JFXButton btn_update;
	@FXML JFXButton btn_del;
	
	//리뷰 서비스 
	private Registry reg;
	private IReviewService irs;
	
	//페이지네이션
	private int from, to, itemsForPage;
	private ObservableList<ReviewVO> allTableData, currentPageData;
	
	//글 전체 정보를 담고 있는 리스트
	public static ArrayList<ReviewVO> list = new ArrayList<>();
	private ArrayList<ReviewVO> click_list = new ArrayList<>();
	
	//로그인한 사용자 정보 받아오기
	MemberVO mv = LoginSession.session;
	ReviewVO vo = new ReviewVO();
	String user ="";
	
	//선택한 글 번호
	String real_no = null;
	
	//글 수정완료 여부 확인 
	int cnt_up = 0;
	int sel_ind = 0;
	//평점 값
	private boolean flag_score = true;

	//파일명 셋팅
    String set_file = "";
    //이미지 파일 경로
  	String reviewImgUrl = "";
    File reviewimgfile;
    Image comimage;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tb_myReview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			irs = (IReviewService) reg.lookup("review");
			System.out.println("rmi 성!공!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		/**
		 * 자신이 쓴 리뷰 리스트 출력하기
		 */
		try {
			
			user = mv.getMem_no();
			vo.setMem_no(user);
			
			list = (ArrayList<ReviewVO>) irs.selectMyReview(vo);
			System.out.println(list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		col_reNo.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_reCom.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_reTitle.setCellValueFactory(new PropertyValueFactory<>("re_title"));
		col_reCont.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_reDate.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		col_reScore.setCellValueFactory(new PropertyValueFactory<>("re_score"));
		
		allTableData = FXCollections.observableArrayList();
		
		allTableData.setAll(list);
		
		tb_myReview.setItems(allTableData);
		
		itemsForPage = 15;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_myReview.setPageCount(totPageCount);	
	
		page_myReview.setPageFactory(this::createPage);
			
		System.out.println("페이지 테스트1");
		
		/**
		 * 리뷰 수정하기
		 */
		btn_update.setOnAction(event1->{
			
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(btn_update.getScene().getWindow());
			dialog.setTitle("리뷰 수정하기");
			
			System.out.println("띄워주기 전단계");
			
			Parent parent = null;
			
			/* 리뷰 수정하기 ---> 화면 팝업 */
			try {
		           parent = FXMLLoader.load(getClass().getResource("Review_update2.fxml"));
		           System.out.println("수정하기 화면으로~!");
		        }catch(IOException ex) {
		           ex.printStackTrace();
		        }
			
			System.out.println("수정하기 화면 띄워주기");
			AnchorPane ap_write  		= (AnchorPane) parent.lookup("#ap_write"); 
			
			JFXTextField txtf_uptitle 	= (JFXTextField) parent.lookup("#txtf_uptitle");//수정 제목
			JFXTextArea txta_upcont 	= (JFXTextArea) parent.lookup("#txta_upcont");//수정 내용
			JFXTextField txtf_filepath 	= (JFXTextField) parent.lookup("#txtf_filepath");
			
			JFXButton btn_upphoto		= (JFXButton) parent.lookup("#btn_upphoto");//사진 추가 버튼
			JFXButton btn_upfinish 		= (JFXButton) parent.lookup("#btn_upfinish");//수정 완료 버튼
			JFXButton btn_cancel 		= (JFXButton) parent.lookup("#btn_cancel");// 취소버튼
			
			JFXRadioButton radbtn_good 	= (JFXRadioButton) parent.lookup("#radbtn_good");//평점 - 라디오 버튼
			JFXRadioButton radbtn_soso 	= (JFXRadioButton) parent.lookup("#radbtn_soso");
			JFXRadioButton radbtn_bad 	= (JFXRadioButton) parent.lookup("#radbtn_bad");
			
			ImageView img_good 			= (ImageView) parent.lookup("#img_good");
			ImageView img_soso 			= (ImageView) parent.lookup("#img_soso");
			ImageView img_bad 			= (ImageView) parent.lookup("#img_bad");
			
			ImageView img_save			= (ImageView) parent.lookup("#img_save");
			
			ToggleGroup radbtn_group = new ToggleGroup();
			
		    txtf_uptitle.setText(tb_myReview.getSelectionModel().getSelectedItem().getRe_title());
    		txta_upcont.setText(tb_myReview.getSelectionModel().getSelectedItem().getRe_cont());
    		int sel_no = tb_myReview.getSelectionModel().getSelectedIndex();
    		
    		sel_ind = (page_myReview.getCurrentPageIndex()) * (itemsForPage)
					+ tb_myReview.getSelectionModel().getSelectedIndex();
			String sel_re_no = list.get(sel_ind).getRe_no();
			
    		try {
				click_list = (ArrayList<ReviewVO>) irs.clickedReviewNo(sel_re_no);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
    		
    		System.out.println(click_list.get(0).getRe_photo()+"-->선택한 글에 대한 사진 경로");
    		if(click_list.get(0).getRe_photo() !=null) {
				reviewImgUrl    = click_list.get(0).getRe_photo();
				reviewimgfile   = new File(reviewImgUrl);
				comimage  		= new Image(reviewimgfile.toURI().toString());
				img_save.setImage(comimage);
			}
    		
    		radbtn_good.setUserData("3");
    		radbtn_soso.setUserData("2");
    		radbtn_bad.setUserData("1");
    		
    		radbtn_good.setSelected(true);
    		
    		radbtn_good.setToggleGroup(radbtn_group);
			radbtn_soso.setToggleGroup(radbtn_group);
			radbtn_bad.setToggleGroup(radbtn_group);
    		
    		btn_upphoto.setOnAction(event2->{
				
				FileInputStream fin = null;
		        FileOutputStream fout = null;
				
				FileChooser chooser = new FileChooser();
				chooser.setTitle("리뷰 사진 선택");
				
				//확장자별로 파일 구분하는 필터 등록하기
				chooser.getExtensionFilters().addAll(
						new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
				);
				
				Stage subStage = new Stage();
				
				File selectFile = chooser.showOpenDialog(subStage);  
				
				set_file = ".\\img\\review\\" + selectFile.getName();
				
				if(selectFile != null) { 
					//이 영역에서 파일 내용을 읽어오는 작업을 수행한다.
					txtf_filepath.setText(set_file);
					
					System.out.println("OPEN : " + selectFile.getPath());
					try {
			            // 파일 읽어오기
					    fin = new FileInputStream(selectFile.getAbsolutePath());
			            fout = new FileOutputStream(set_file);
			            
			            int c;
			             
			             while((c = fin.read()) != -1) {
			                fout.write(c);
			             }
			             
			             fin.close();
			             fout.close();
			             
			          } catch (IOException e7) {
			             e7.printStackTrace();
			          }
						
				}
			
			});		
    		
		    btn_upfinish.setOnAction(event2->{
		    	try {
			    	
	        		//수정할 때 입력받아야 되는 값 : re_no, com_id, re_title, re_date, re_cont, re_score
		    		user = mv.getMem_no();
		    		vo.setMem_no(user);
	        		vo.setRe_no(sel_re_no);//
	        		
	        		vo.setRe_title(txtf_uptitle.getText()); //
	        		vo.setRe_cont(txta_upcont.getText());//
	        		vo.setRe_photo(txtf_filepath.getText());
	        		
	        		String url = radbtn_group.getSelectedToggle().getUserData().toString();
					vo.setRe_score(url);
					radbtn_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

						@Override
						public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
							String url = newValue.getUserData().toString();
							vo.setRe_score(url);	
						}
					});
	        		
	        		if(txtf_uptitle.getText()!=null ) {
	        			vo.setRe_title(txtf_uptitle.getText().toString());
	        		}else {
	        			errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
	        			return;
	        		}
	        		
	        		if(txta_upcont.getText()!=null) {
	        			vo.setRe_cont(txta_upcont.getText().toString());
	        		}else {
	        			errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
	        			return;
	        		}
	        		
	        		if(radbtn_good.isSelected()) {
	        			System.out.println(radbtn_good.getUserData().toString());
	        			vo.setRe_score(radbtn_good.getUserData().toString());
	        			flag_score = false;					
	        		}
	        		
	        		if(radbtn_soso.isSelected()) {
	        			System.out.println(radbtn_soso.getText());
	        			vo.setRe_score(radbtn_soso.getUserData().toString());
	        			flag_score = false;					
	        		}
	        		
	        		if(radbtn_bad.isSelected()) {
	        			System.out.println(radbtn_bad.getText());
	        			vo.setRe_score(radbtn_bad.getUserData().toString());
	        			flag_score = false;					
	        		}
	    			
	        		//수정된 내용 업데이트!
	        		cnt_up = irs.updateReview(vo);
	        		
	        		if(cnt_up > 0) {
	    				infoMsg("성공!", "게시글이 성공적으로 수정되었습니다!");
	    				refresh();
	    				dialog.close();
	    			}
	        		
	        		if(cnt_up<0) {
	        			System.out.println("수정 안됨..");
	        			errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
	        		}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
		    	
		    	
		    });
		    
		    
		    Scene scene = new Scene(parent);
			
        	System.out.println("다시 사용자 리뷰 관리 화면 돌아가기");
        	
			dialog.setScene(scene);
			dialog.setResizable(false); 
			dialog.show();
			
			btn_cancel.setOnAction(e->{
				dialog.close();
			});
		});
		
		/**
		 * 리뷰 삭제하기
		 */
		btn_del.setOnAction(event3->{
			
			int sel_no = tb_myReview.getSelectionModel().getSelectedIndex();
	  		
    		sel_ind = (page_myReview.getCurrentPageIndex()) * (itemsForPage)
					+ tb_myReview.getSelectionModel().getSelectedIndex();
			String sel_re_no = list.get(sel_ind).getRe_no();
			
			user = mv.getMem_no();
			vo.setMem_no(user);
			vo.setRe_no(sel_re_no);
			
			try {
				int del_re = irs.deleteReview(vo);
				
				if(del_re > 0) {
    				infoMsg("성공!", "게시글이 성공적으로 삭제되었습니다!");
    				refresh(); //새로고침
    			}
        		
        		if(del_re < 0) {
        			System.out.println("삭제 안됨..");
        			errMsg("실패!", "삭제가 안된 원인을 찾아보세요.."); 
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
		tb_myReview.setItems(getTableViewData(from, to));
		
		System.out.println("페이지 메서드1");
		
		return tb_myReview;
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
	
	//수정이나, 삭제 후 이전화면에 셋팅해주기
	private void refresh() {
		
		try {
			
			user = mv.getMem_no();
			vo.setMem_no(user);
			
			list = (ArrayList<ReviewVO>) irs.selectMyReview(vo);
			System.out.println(list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		col_reNo.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_reCom.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		col_reTitle.setCellValueFactory(new PropertyValueFactory<>("re_title"));
		col_reCont.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_reDate.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		col_reScore.setCellValueFactory(new PropertyValueFactory<>("re_score"));
		
		allTableData = FXCollections.observableArrayList();
		
		allTableData.setAll(list);
		
		tb_myReview.setItems(allTableData);
		
		itemsForPage = 15;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_myReview.setPageCount(totPageCount);	
	
		page_myReview.setPageFactory(this::createPage);
		
	}
}

