package kr.or.ddit.mp.view.salescom;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.mp.main.LoginSession;
import kr.or.ddit.mp.service.salescom.ISalesComService;
import kr.or.ddit.mp.vo.eatdeal.EatqaVO;
import kr.or.ddit.mp.vo.eatdeal.SalesVO;
import kr.or.ddit.mp.vo.member.ComVO;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class SalesComController implements Initializable{

	@FXML AnchorPane anpane_sales;
	@FXML TableView<SalesVO> tb_sales;
	@FXML TableColumn<SalesVO, String> col_salNo;
	@FXML TableColumn<SalesVO, String> col_salName;
	@FXML TableColumn<SalesVO, String> col_salQty;
	@FXML TableColumn<SalesVO, String> col_salPri;
	@FXML TableColumn<SalesVO, String> col_salTotal;
	@FXML Pagination page_sales;
	@FXML JFXButton btn_chart;
	
	
	private int from, to, itemsForPage;
	private ObservableList<SalesVO> allTableData, currentPageData;
	private Registry reg;
	private ISalesComService salSer;
	
	ArrayList<SalesVO> list;
	ComVO cv = LoginSession.comsession;
	String com_id = cv.getCom_id();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_sales.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			reg = LocateRegistry.getRegistry("localhost", 8429);
			salSer = (ISalesComService) reg.lookup("SalesService");
			System.out.println("RMI성공");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		/////////////////////
		col_salNo.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_salName.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		col_salQty.setCellValueFactory(new PropertyValueFactory<>("order_qty"));
		col_salPri.setCellValueFactory(new PropertyValueFactory<>("eat_price"));
		col_salTotal.setCellValueFactory(new PropertyValueFactory<>("order_price"));
		/////////////////////
		
		viewTb();
		
	}
	private void viewTb() {
		try {
			list = (ArrayList<SalesVO>) salSer.getSalesComEatdeal(com_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		btn_chart.setVisible(false);
		if(list.size() > 0) {
			btn_chart.setVisible(true);
		}
		
		allTableData = FXCollections.observableArrayList();
		
		allTableData.setAll(list);
		itemsForPage = 10;
		
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size()/itemsForPage  
				: allTableData.size()/itemsForPage + 1; 
				
		page_sales.setPageCount(totPageCount);	
		page_sales.setPageFactory(this::createPage);
		
	}
	
	private ObservableList<SalesVO> getTableViewData(int from, int to){
		
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
		tb_sales.setItems(getTableViewData(from, to));
		
		return tb_sales;
	}
	

	@FXML public void Clickview(ActionEvent event) {
		
				
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(btn_chart.getScene().getWindow());
		dialog.setTitle("매출 조회");
		
		Parent parent = null;

		try {
           parent = FXMLLoader.load(getClass().getResource("anchor_popup2.fxml"));
        }catch(IOException ex) {
           ex.printStackTrace();
        }
		AnchorPane anc_howWay = (AnchorPane) parent.lookup("#anc_howWay");
		anc_howWay.setStyle("-fx-background-color: #ffffff");

		CategoryAxis xAxis = new CategoryAxis();

		NumberAxis yAxis = new NumberAxis();

		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

		bc.setTitle("EAT딜 매출");
		bc.setStyle("-fx-background-color: #ffffff");
		bc.setMaxSize(400, 400);
		xAxis.setLabel("EAT딜 이름");
		yAxis.setLabel("총 매출");
		
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		for (int i = 0; i < list.size(); i++) {
			String eatName = list.get(i).getEat_name();
			int total = Integer.parseInt(list.get(i).getOrder_price());
			int one = Integer.parseInt(list.get(i).getEat_price());
			ser1.getData().add(new XYChart.Data<>(eatName, total));
			ser2.getData().add(new XYChart.Data<>(eatName, one));
		}
		
		

		// BarChart에 나타낼 데이터 구성하기
		ser1.setName("매출액");
		ser2.setName("개당 가격");
	
		// 시리즈 넣은 만큼 한 행에 나오는 개수가 달라짐
		bc.getData().addAll(ser1, ser2);
		anc_howWay.getChildren().add(bc);
		
	      
        Scene scene = new Scene(parent);
         
        dialog.setScene(scene);
        dialog.setResizable(true); 
        dialog.show();
		
	}
	
	
	private final NumberAxis yAxis = new NumberAxis();
	private final CategoryAxis xAxis = new CategoryAxis();
	private final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

	private void setMaxBarWidth(double maxBarWidth, double minCategoryGap){
	    double barWidth=0;
	    do{
	        double catSpace = xAxis.getCategorySpacing();
	        double avilableBarSpace = catSpace - (bc.getCategoryGap() + bc.getBarGap());
	        barWidth = (avilableBarSpace / bc.getData().size()) - bc.getBarGap();
	        if (barWidth >maxBarWidth){
	            avilableBarSpace=(maxBarWidth + bc.getBarGap())* bc.getData().size();
	            bc.setCategoryGap(catSpace-avilableBarSpace-bc.getBarGap());
	        }
	    } while(barWidth>maxBarWidth);

	    do{
	        double catSpace = xAxis.getCategorySpacing();
	        double avilableBarSpace = catSpace - (minCategoryGap + bc.getBarGap());
	        barWidth = Math.min(maxBarWidth, (avilableBarSpace / bc.getData().size()) - bc.getBarGap());
	        avilableBarSpace=(barWidth + bc.getBarGap())* bc.getData().size();
	        bc.setCategoryGap(catSpace-avilableBarSpace-bc.getBarGap());
	    } while(barWidth < maxBarWidth && bc.getCategoryGap()>minCategoryGap);
	}
	
}
