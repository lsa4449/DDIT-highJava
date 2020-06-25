package kr.or.ddit.mp.view.admin;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.mp.service.admin.AdminService;
import kr.or.ddit.mp.vo.admin.MemberVO;

public class AllGoodComController  implements Initializable {

	
	@FXML
	TableView<MemberVO> tableview;
	@FXML
	TableColumn<MemberVO, String> tb_comName;
	@FXML
	TableColumn<MemberVO, String> tb_name;
	@FXML
	TableColumn<MemberVO, String> tb_comId;
	@FXML
	TableColumn<MemberVO, String> tb_hp;
	@FXML 
	TableColumn<MemberVO, String> tb_comTel;
	@FXML 
	Pagination page_allMember;

	
	private Registry reg;
	private AdminService as ;

	private int from, to, itemsForPage;
	private ObservableList<MemberVO> allTableData, currentPageData;

	
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
		
		tb_comName.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		tb_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_comId.setCellValueFactory(new PropertyValueFactory<>("com_id"));
		tb_hp.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		tb_comTel.setCellValueFactory(new PropertyValueFactory<>("com_tel"));

		allTableData = FXCollections.observableArrayList();
		ArrayList<MemberVO> list = new ArrayList<>();

		try {
			list = (ArrayList<MemberVO>) as.getAllGoodCom();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allTableData.setAll(list);

		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();
		
		itemsForPage = 19;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
				page_allMember.setPageCount(totPageCount);	
				
				page_allMember.setPageFactory(this::createPage);
		
		
	}
	
	
	private ObservableList<MemberVO> getTableviewData(int from, int to){
		
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
			tableview.setItems(getTableviewData(from, to));
			
			return tableview;
	}

	
}
