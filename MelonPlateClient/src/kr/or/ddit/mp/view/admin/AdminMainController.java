package kr.or.ddit.mp.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.admin.AdminService;
import kr.or.ddit.mp.vo.admin.MemberVO;

public class AdminMainController implements Initializable {

	@FXML
	TableView<MemberVO> tableview;
	@FXML
	TableColumn<MemberVO, String> tb_grade;
	@FXML
	TableColumn<MemberVO, String> tb_name;
	@FXML
	TableColumn<MemberVO, String> tb_hp;
	@FXML
	TableColumn<MemberVO, String> tb_melon;
	@FXML
	TableColumn<MemberVO, String> tb_com;
	@FXML
	Button btn_addMember;
	@FXML
	Button btn_deleteMember;
	@FXML
	Button btn_member;
	@FXML
	Button btn_company;
	@FXML
	Pagination page_allMember;
	@FXML
	AnchorPane an_adminMain;

	private Registry reg;
	private AdminService as;

	private int from, to, itemsForPage;
	private ObservableList<MemberVO> allTableData, currentPageData;

	kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	String ss = vo.getMem_no();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);

			as = (AdminService) reg.lookup("AdminService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		assert tableview != null : "fx:id=\"tableview\" was not injected : check your FXML file 'AdminMain.fxml'.";
		assert page_allMember != null : "fx:id=\"page_allMember\" was not injected : check your FXML file 'BoardMain.fxml'.";

		tb_grade.setCellValueFactory(new PropertyValueFactory<>("mem_grade"));
		tb_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_hp.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		tb_melon.setCellValueFactory(new PropertyValueFactory<>("melonage"));
		tb_com.setCellValueFactory(new PropertyValueFactory<>("com_name"));

		allTableData = FXCollections.observableArrayList();
		ArrayList<MemberVO> list = new ArrayList<>();

		try {
			list = (ArrayList<MemberVO>) as.getAllMemberCom();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allTableData.setAll(list);

		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();

		itemsForPage = 19;

		int totPageCount = allTableData.size() % itemsForPage == 0 ? allTableData
				.size() / itemsForPage
				: allTableData.size() / itemsForPage + 1;

		page_allMember.setPageCount(totPageCount);

		page_allMember.setPageFactory(this::createPage);

		tableview.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (tableview.getSelectionModel().getSelectedItem() != null) {

					btn_addMember.setDisable(false);
					btn_deleteMember.setDisable(false);
					btn_member.setDisable(false);
					btn_company.setDisable(false);

				}
			}
		});
	}

	private ObservableList<MemberVO> getTableviewData(int from, int to) {

		currentPageData = FXCollections.observableArrayList();
		int totSize = allTableData.size();
		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}

		return currentPageData;
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableview.setItems(getTableviewData(from, to));

		return tableview;
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

	@FXML
	public void addAdmin(ActionEvent event) {

		MemberVO mvo = new MemberVO();
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_addMember.getScene().getWindow());
		dialog.setTitle("관리자 계정 생성");

		Parent parent = null;

		try {
			parent = FXMLLoader.load(getClass().getResource("addAdmin.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		TextField mem_id = (TextField) parent.lookup("#txtf_adminId");
		TextField mem_name = (TextField) parent.lookup("#txtf_adminName");
		PasswordField mem_pw = (PasswordField) parent.lookup("#pwf_pw");
		PasswordField mem_pwCheck = (PasswordField) parent.lookup("#pwf_pwCheck");

		Button btn_finish = (Button) parent.lookup("#btn_finish");
		Button btn_cancle = (Button) parent.lookup("#btn_cancle");

		btn_finish.setOnAction(ok -> {

			if (mem_pw.getText().equals(mem_pwCheck.getText())) {

				mvo.setMem_id(mem_id.getText());
				mvo.setMem_name(mem_name.getText());
				mvo.setMem_pw(mem_pw.getText());
				infoMsg("성공!", "성공적으로 등록되었습니다!");
			} else if (!mem_pw.getText().equals(mem_pwCheck.getText())) {
				errMsg("실패!", "패스워드가 일치하지 않습니다.");
				return;
			} else {
				errMsg("실패!", "빈 항목이 있는지 확인해주세요");
				return;
			}

			int cnt;
			try {
				cnt = as.insertAdmin(mvo);
				if (cnt > 0) {

					ArrayList<MemberVO> list = new ArrayList<>();
					list = (ArrayList<MemberVO>) as.getAllMemberCom();
					allTableData.setAll(list);
					page_allMember.setPageFactory(this::createPage);
					dialog.close();

				}
			} catch (RemoteException e) {

				e.printStackTrace();
			}

		});

		btn_cancle.setOnAction(cancle -> {
			dialog.close();
		});

		Scene scene = new Scene(parent);

		dialog.setScene(scene);
		dialog.setResizable(false); // 크기 고정 true는 가변
		dialog.show();

	}

	@FXML
	public void deleteMember(ActionEvent event) throws Exception {
		int select_ind = tableview.getSelectionModel().getSelectedIndex();
		if (select_ind <= -1) {
			errMsg("오류!", "데이터를 선택해주세요!!");
			return;
		}
		MemberVO mvo = new MemberVO();
		String mem_no = tableview.getSelectionModel().getSelectedItem()
				.getMem_no();
		mvo.setMem_no(mem_no);
		if (vo.getMem_no().equals(mvo.getMem_no())) {
			errMsg("오류!", "나 자신은 삭제할 수 없습니다!!");
		} else {

			int cnt = as.deleteMember(mvo);

			if (cnt > 0) {
				infoMsg("삭제 결과", cnt + "명의 회원이 삭제되었습니다.");
				ArrayList<MemberVO> list = new ArrayList<>();
				list = (ArrayList<MemberVO>) as.getAllMemberCom();
				allTableData.setAll(list);
				page_allMember.setPageFactory(this::createPage);
			} else {
				errMsg("오류!", "일시적인 오류가 발생했습니다!");
				page_allMember.setPageFactory(this::createPage);
			}
		}
	}

	@FXML
	public void inquiryMem(ActionEvent event) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("member.fxml"));
			an_adminMain.getChildren().clear();
			an_adminMain.getChildren().add(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void inquiryCom(ActionEvent event) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("com.fxml"));
			an_adminMain.getChildren().clear();
			an_adminMain.getChildren().add(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
