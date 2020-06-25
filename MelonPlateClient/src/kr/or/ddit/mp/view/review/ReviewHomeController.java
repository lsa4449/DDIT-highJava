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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.review.IReviewService;
import kr.or.ddit.mp.vo.member.MemberVO;
import kr.or.ddit.mp.vo.review.ReviewVO;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ReviewHomeController implements Initializable {
	
	@FXML TableView<ReviewVO> tb_review;
	@FXML TableColumn<ReviewVO, String> col_reWriter;
	@FXML TableColumn<ReviewVO, String> col_reTitle;
	@FXML TableColumn<ReviewVO, String> col_reCont;
	@FXML TableColumn<ReviewVO, String> col_redate;
	@FXML TableColumn<ReviewVO, String> col_reScore;
	@FXML TableColumn<ReviewVO, String> col_reUp;
	@FXML TableColumn<ReviewVO, String> col_reDown;
	@FXML HBox hb_image;
	@FXML Pagination page_Review;
	@FXML ImageView img_write;
	@FXML AnchorPane ap_home;
	
	@FXML Text txt_title;
	@FXML Text txt_cont;
	@FXML Text txt_nick;
	@FXML Text txt_date;
	@FXML Text txt_up;
	@FXML Text txt_down;
	
	@FXML ImageView img_user;
	@FXML ImageView img_down;
	@FXML ImageView img_up;
	@FXML ImageView img_de_review;
	@FXML ImageView img_score;
	
	@FXML JFXButton btn_down;
	@FXML JFXButton btn_up;
	@FXML JFXButton btn_write;
	
	//리뷰 서비스 
	private Registry reg;
	private IReviewService irs;
	
	//페이지네이션
	private int from, to, itemsForPage;
	private ObservableList<ReviewVO> allTableData, currentPageData;
	
	private AnchorPane newPane;
	
	public static String get_rev_com_id = "";
	
	//글 전체 정보를 담고 있는 컬럼
	private ArrayList<ReviewVO> list = new ArrayList<>();
	private ArrayList<ReviewVO> click_list = new ArrayList<>();
	
	int sel_ind = 0;
	//vo
	private ReviewVO vo = new ReviewVO();
	//회원정보 받기
	String sel_comId = null;
	String user = "";
	MemberVO mv = LoginSession.session;

	//이미지 파일 경로
	String reviewImgUrl = "";
    File reviewimgfile;
    Image comimage;

    //평점 값
    private String re_score = null;
	private boolean flag_score = true;
    
	//파일명 셋팅
    String set_file = "";
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_review.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		tb_review.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
				reg = LocateRegistry.getRegistry("localhost", 8429);
				irs = (IReviewService) reg.lookup("review");
				System.out.println("rmi 성!공!");
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		 
		assert tb_review != null	 : "fx:id=\"tableView\" was not injected : check your FXML file 'Review_home.fxml'.";
		assert page_Review != null	 : "fx:id=\"pagination\" was not injected : check your FXML file 'Review_home.fxml'.";
		
		col_reWriter.setCellValueFactory(new PropertyValueFactory<>("mem_nick"));
		col_reTitle.setCellValueFactory(new PropertyValueFactory<>("re_title"));
		col_reCont.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_redate.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		col_reScore.setCellValueFactory(new PropertyValueFactory<>("re_score"));
		col_reUp.setCellValueFactory(new PropertyValueFactory<>("re_up"));
		col_reDown.setCellValueFactory(new PropertyValueFactory<>("re_down"));
		
		allTableData = FXCollections.observableArrayList();
		
		System.out.println("table 테스트");
		
		/**
		 * 리뷰 리스트 출력하기 ---------------- 리뷰 작성하기 화면이랑 통일시켜주기★★★★★★★★
		 */
		try {
			list = (ArrayList<ReviewVO>) irs.selectReviewAll(get_rev_com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_review.setItems(allTableData);
		
        setPagination();
		
		
		tb_review.setOnMouseClicked(selectView->{
			LoginSession.ch_up 		= 0;
			LoginSession.ch_down 	= 0;
			LoginSession.isClicked 	= true;
			sel_ind = (page_Review.getCurrentPageIndex()) * (itemsForPage)
					+ tb_review.getSelectionModel().getSelectedIndex();
			String sel_re_no = list.get(sel_ind).getRe_no();
			
			try {
				click_list = (ArrayList<ReviewVO>) irs.clickedReviewNo(sel_re_no);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			/*
			try {
	      		
        		//선택된 글 찾아주려면 필요한 조건 : 닉네임(mem_nick), 업체사업자번호(com_id)
        		for(int i=0; i<list.size(); i++) {
        			if(tb_review.getSelectionModel().getSelectedItem().getMem_nick().equals(list.get(i).getMem_nick())) {
        				sel_comId = list.get(i).getCom_id(); //선택한 글의 사업자번호
        			}
        		}
        		vo.setMem_nick(tb_review.getSelectionModel().getSelectedItem().getMem_nick());
        		vo.setCom_id(sel_comId);
            	
        		list = (ArrayList<ReviewVO>) irs.selectReview(vo);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
			*/
			
			if(click_list.size() == 0) {
				return;
			}
			
			txt_title.setText(click_list.get(0).getRe_title());
			txt_cont.setText(click_list.get(0).getRe_cont()); 
			txt_nick.setText(click_list.get(0).getMem_nick());
			txt_date.setText(click_list.get(0).getRe_date());
			txt_up.setText(click_list.get(0).getRe_up().toString());		
			txt_down.setText(click_list.get(0).getRe_down());	
			
			/**
        	 * 리뷰 사진 뜨게 하기
            */
			reviewImgUrl = "";
			if(click_list.get(0).getRe_photo() !=null) {
				reviewImgUrl    = click_list.get(0).getRe_photo();
				reviewimgfile   = new File(reviewImgUrl);
				comimage  		= new Image(reviewimgfile.toURI().toString());
				img_de_review.setImage(comimage);
			}
            
            /**
        	 * 평점 이미지 뜨게 하기
            */
        	if(click_list.get(0).getRe_score().equals("3")) {
        		img_score.setImage(new Image(getClass().getResource("happy.png").toString()));
        	}
        	else if(click_list.get(0).getRe_score().equals("2")) {
        		img_score.setImage(new Image(getClass().getResource("so.png").toString()));
        		
        	}else if(click_list.get(0).getRe_score().equals("1")) {
        		img_score.setImage(new Image(getClass().getResource("shouting.png").toString()));
        	}
            
			//추천을 클릭하면
	    	btn_up.setOnAction(e->{
	    		
	    		String int_up = String.valueOf(Integer.parseInt(click_list.get(0).getRe_up())+1);
	    		
	    		int cnt_up = 0;
	    		
	    		//로그인한 사람은 한번만 할 수 있게 하기
	    		int ch_click = LoginSession.ch_up;
	    		System.out.println(click_list.get(0).getRe_no());
	    		
	    		if(!LoginSession.isClicked || ch_click != 0) {
	    			errMsg("실패!", "추천은 한번만 하실 수 있습니다.");
	    			return;
	    		}
	    		try {
					cnt_up = irs.upReview(click_list.get(0).getRe_no());
					
					if(cnt_up > 0) {
						infoMsg("성공!", "게시글을 추천하셨습니다!");
						txt_up.setText(int_up);
						LoginSession.ch_up++;
						LoginSession.isClicked = false;
						refresh();
						
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
	    	});
	    	
			//신고를 클릭하면
	    	btn_down.setOnAction(e->{
	    		
	    		String int_down = String.valueOf(Integer.parseInt(click_list.get(0).getRe_down())+1);

	    		int cnt_down = 0;
	    		
	    		//로그인한 사람은 한번만 할 수 있게 하기
	    		int ch_click = LoginSession.ch_down; //0
	    		
	    		if(!LoginSession.isClicked || ch_click != 0) {
	    			errMsg("실패!", "신고는 한번만 하실 수 있습니다.");
	    			return;
	    		}
	    		try {
					cnt_down = irs.downReview(click_list.get(0).getRe_no());
					
					if(cnt_down > 0) {
						infoMsg("성공!", "게시글을 신고하셨습니다!");
						txt_down.setText(int_down);
						LoginSession.ch_down++;
						LoginSession.isClicked = false;

						refresh();
						
					}else {
						errMsg("실패!", "다시 한번 더 클릭해주세요.");
						return;
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
	    		
	    		
	    	});
	    	
		});
    	
		btn_write.setOnAction(write->{
		
			Stage dialog = new Stage(StageStyle.UTILITY);
        	
        	dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(tb_review.getScene().getWindow());
			dialog.setTitle("리뷰 작성하기");
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("Review_writer.fxml"));
	           System.out.println("point 3");
	           
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			
			AnchorPane ap_write			= (AnchorPane) parent.lookup("#ap_write"); 

			JFXButton btn_finish		= (JFXButton) parent.lookup("#btn_finish");
			JFXButton btn_cancel		= (JFXButton) parent.lookup("#btn_cancel");
			JFXButton btn_photo			= (JFXButton) parent.lookup("#btn_photo");
			
			JFXTextField txtf_title		= (JFXTextField) parent.lookup("#txtf_title");
			JFXTextArea txta_cont		= (JFXTextArea) parent.lookup("#txta_cont");
			
			JFXRadioButton radbtn_good 	= (JFXRadioButton) parent.lookup("#radbtn_good");
			JFXRadioButton radbtn_soso	= (JFXRadioButton) parent.lookup("#radbtn_soso");
			JFXRadioButton radbtn_bad	= (JFXRadioButton) parent.lookup("#radbtn_bad");
			
			JFXTextField txtf_filepath	= (JFXTextField) parent.lookup("#txtf_filepath");
			ToggleGroup radbtn_group = new ToggleGroup();
			
			
			
			radbtn_good.setUserData("3");
			radbtn_soso.setUserData("2");
			radbtn_bad.setUserData("1");
			radbtn_good.setSelected(true);
			
			radbtn_good.setToggleGroup(radbtn_group);
			radbtn_soso.setToggleGroup(radbtn_group);
			radbtn_bad.setToggleGroup(radbtn_group);
			

			
        	btn_photo.setOnAction(event2->{
				
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
        	
        	
        	/**
        	 * 리뷰 작성 완료 버튼 
        	*/
			btn_finish.setOnAction(e->{
				
				
				
				int in_cnt = 0;  
				
				user = mv.getMem_no();
				
				//리뷰 작성 테스트----입력에 없는 것만
				vo.setCom_id(get_rev_com_id); 
				vo.setMem_no(user);
				
				if(txtf_title.getText()!=null ) {
					vo.setRe_title(txtf_title.getText());
				}else {
					errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
					return;
				}
				
				if(txta_cont.getText()!=null) {
					vo.setRe_cont(txta_cont.getText());
				}else {
					errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
					return;
				}
				/*
				if(radbtn_good.isSelected()) {
					System.out.println(radbtn_good.getText());
					vo.setRe_score(radbtn_good.getUserData());
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
				if(flag_score==true) {
					errMsg("실패!", "빈 항목이 있는지 확인하여 주세요."); 
					return;
				}else{
					vo.setRe_score(re_score);	
				}
				*/
				String url = radbtn_group.getSelectedToggle().getUserData().toString();
				vo.setRe_score(url);
				radbtn_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

					@Override
					public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
						String url = newValue.getUserData().toString();
						vo.setRe_score(url);	
					}
				});
				
				
				try {
					vo.setRe_photo(txtf_filepath.getText());
					
					in_cnt = irs.insertReview(vo);
					
					if(in_cnt > 0) {
						infoMsg("성공!", "게시글이 성공적으로 등록되었습니다!");
						refresh();
					}else {
						errMsg("실패!", "빈 항목이 있는지 확인하여 주세요.");
					}
					
					
				} catch (RemoteException e5) {
					e5.printStackTrace();
				}
				
				dialog.close();
			});
			
			
			
			
			//취소 버튼
			btn_cancel.setOnAction(e->{

				dialog.close();
			});
			
			
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.setResizable(false); 
			dialog.show();

		});
		
		refresh();
				
	}
	
	//페이지네이션----------------------------------------------------	
	private void setPagination() {

		col_reWriter.setCellValueFactory(new PropertyValueFactory<>("mem_nick"));
		col_reTitle.setCellValueFactory(new PropertyValueFactory<>("re_title"));
		col_reCont.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_redate.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		col_reScore.setCellValueFactory(new PropertyValueFactory<>("re_score"));
		col_reUp.setCellValueFactory(new PropertyValueFactory<>("re_up"));
		col_reDown.setCellValueFactory(new PropertyValueFactory<>("re_down"));
		
		allTableData = FXCollections.observableArrayList();
		
		try {
			list = (ArrayList<ReviewVO>) irs.selectReviewAll(get_rev_com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_review.setItems(allTableData);	
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_Review.setPageCount(totPageCount);	
			
				page_Review.setPageFactory(this::createPage);

	}
	
	//페이지네이션에 필요한 메서드 - 1
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		tb_review.setItems(getTableViewData(from, to));
		
		return tb_review;
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

	/**
	 * 리뷰 상세 내용 조회하기 --> 팝업에서 수정
	 */
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
			list = (ArrayList<ReviewVO>) irs.selectReviewAll(get_rev_com_id);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData.setAll(list);
		
		tb_review.setItems(allTableData);
		
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_Review.setPageCount(totPageCount);	
	
				page_Review.setPageFactory(this::createPage);
		
	}
}
