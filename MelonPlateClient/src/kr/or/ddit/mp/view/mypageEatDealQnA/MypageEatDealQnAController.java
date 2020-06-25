package kr.or.ddit.mp.view.mypageEatDealQnA;

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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.mp.service.mypageEatDealQnA.MypageEatDealQnAService;
import kr.or.ddit.mp.vo.mypageEatDealQnA.EatqaVO;

public class MypageEatDealQnAController implements Initializable {

	@FXML
	TableView<EatqaVO> tableview;
	@FXML
	TableColumn<EatqaVO, String> col_QnANo;
	@FXML
	TableColumn<EatqaVO, String> col_QnATitle;
	@FXML
	TableColumn<EatqaVO, String> col_QnAContent;
	@FXML
	TableColumn<EatqaVO, String> col_QnAIndate;
	@FXML
	TableColumn<EatqaVO, String> col_answerStatus;
	@FXML
	Pagination page_allMember;
	
	private String sessoin = "70";

	private Registry reg;
	private MypageEatDealQnAService mcs;

	private int from, to, itemsForPage;
	private ObservableList<EatqaVO> allTableData, currentPageData;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			mcs = (MypageEatDealQnAService) reg.lookup("server");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		assert tableview != null : "fx:id=\"tableview\" wmcs not injected : check your FXML file 'AdminMain.fxml'.";
		assert page_allMember != null : "fx:id=\"page_allMember\" wmcs not injected : check your FXML file 'BoardMain.fxml'.";

		col_QnANo.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_QnATitle.setCellValueFactory(new PropertyValueFactory<>("eatqa_title"));
		col_QnAContent.setCellValueFactory(new PropertyValueFactory<>("eatqa_cont"));
		col_QnAIndate.setCellValueFactory(new PropertyValueFactory<>("in_date"));
		col_answerStatus.setCellValueFactory(new PropertyValueFactory<>("eatqa_com_st"));
		
        
		
		allTableData = FXCollections.observableArrayList();
		ArrayList<EatqaVO> list = new ArrayList<>();
		
		try {
			EatqaVO evo = new EatqaVO();
			
			//나중에 회원 번호 받을 곳
			evo.setMem_no(sessoin);
			
			list = (ArrayList<EatqaVO>) mcs.getMyEatDealQnA(evo);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		
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
	

	private ObservableList<EatqaVO> getTableviewData(int from, int to) {

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
