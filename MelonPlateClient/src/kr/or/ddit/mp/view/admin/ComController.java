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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.mp.service.admin.AdminService;
import kr.or.ddit.mp.vo.admin.MemberVO;

public class ComController implements Initializable {


	@FXML
	TableView<MemberVO> tableview;
	@FXML
	TableColumn<MemberVO, String> tb_grade;
	@FXML
	TableColumn<MemberVO, String> tb_name;
	@FXML
	TableColumn<MemberVO, String> tb_hp;
	@FXML 
	TableColumn<MemberVO, String> tb_com;
	@FXML 
	Pagination page_allMember;
	@FXML 
	Button btn_comCheck;
	@FXML
	AnchorPane an_adminMain;
	
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
		
		tb_grade.setCellValueFactory(new PropertyValueFactory<>("mem_grade"));
		tb_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_hp.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		tb_com.setCellValueFactory(new PropertyValueFactory<>("com_name"));

		allTableData = FXCollections.observableArrayList();
		ArrayList<MemberVO> list = new ArrayList<>();

		try {
			list = (ArrayList<MemberVO>) as.getAllCom();
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
	@FXML
	public void comCheck(ActionEvent event) throws Exception {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("adminMain.fxml"));
			an_adminMain.getChildren().clear();
			an_adminMain.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
