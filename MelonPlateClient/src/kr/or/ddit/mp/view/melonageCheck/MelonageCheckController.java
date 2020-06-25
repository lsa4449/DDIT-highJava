package kr.or.ddit.mp.view.melonageCheck;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.melonageCheck.MileageCheckService;
import kr.or.ddit.mp.vo.melonageCheck.MileageVO;

public class MelonageCheckController implements Initializable {

	@FXML
	TableView<MileageVO> tableview;
	@FXML
	TableColumn<MileageVO, String> tb_status;
	@FXML
	TableColumn<MileageVO, String> tb_saveList;
	@FXML
	TableColumn<MileageVO, String> tb_save;
	@FXML
	TableColumn<MileageVO, String> tb_used;
	@FXML
	TableColumn<MileageVO, String> tb_amount;
	@FXML
	TableColumn<MileageVO, String> tb_indate;
	@FXML
	TableColumn<MileageVO, String> tb_update;
	@FXML
	Pagination page_allMember;
	@FXML
	Text melonage;

	private Registry reg;
	private MileageCheckService mcs;

	private int from, to, itemsForPage;
	private ObservableList<MileageVO> allTableData, currentPageData;
	
	kr.or.ddit.mp.vo.member.MemberVO vo = LoginSession.session;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			mcs = (MileageCheckService) reg.lookup("MileageCheckService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		assert tableview != null : "fx:id=\"tableview\" wmcs not injected : check your FXML file 'AdminMain.fxml'.";
		assert page_allMember != null : "fx:id=\"page_allMember\" wmcs not injected : check your FXML file 'BoardMain.fxml'.";

		tb_status.setCellValueFactory(new PropertyValueFactory<>("mel_status"));
		tb_saveList.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		tb_save.setCellValueFactory(new PropertyValueFactory<>("mel_add"));
		tb_used.setCellValueFactory(new PropertyValueFactory<>("mel_use"));
		tb_amount.setCellValueFactory(new PropertyValueFactory<>("order_price"));
		tb_indate.setCellValueFactory(new PropertyValueFactory<>("mel_indate"));
		tb_update.setCellValueFactory(new PropertyValueFactory<>("mel_update"));
		
		
		
		
		allTableData = FXCollections.observableArrayList();
		ArrayList<MileageVO> list = new ArrayList<>();
		ArrayList<MileageVO> list2 = new ArrayList<>();
		
		try {
			String ss = vo.getMem_no();
			MileageVO mvo = new MileageVO();
			//나중에 회원 번호 받을 곳
			mvo.setMem_no("40");
			mvo.setMem_no(ss);
			
			list = (ArrayList<MileageVO>) mcs.getAddUsedMelonage(mvo);
			list2 = (ArrayList<MileageVO>) mcs.getAllMelonage(mvo);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(list2.size() ==0) {
			melonage.setText("0");
		}
		
		for (int i = 0; i < list.size(); i++) {
			String indate = list.get(i).getMel_indate();
			if (indate!=null) {
				list.get(i).setMel_status("적립");
			} else if(indate==null) {
				list.get(i).setMel_status("사용");
			}

		}
		for (int i = 0; i < list2.size(); i++) {
			String melon = list2.get(0).getMelonage();
			melonage.setText(melon);
			
		}
		
		
	/*	for (int i = 0; i < list2.size(); i++) {
			String melon = list2.get(0).getMelonage();
			melonage.setText(melon);
			
		}
		*/
		
		allTableData.setAll(list);

		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();

		itemsForPage = 19;

		int totPageCount = allTableData.size() % itemsForPage == 0 ? allTableData.size() / itemsForPage
				: allTableData.size() / itemsForPage + 1;

		page_allMember.setPageCount(totPageCount);

		page_allMember.setPageFactory(this::createPage);

		tableview.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {

			
			}
		});
	}

	private ObservableList<MileageVO> getTableviewData(int from, int to) {

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

}
