package kr.or.ddit.prod.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdController implements Initializable {
	
	private ProdService service = ProdService.getInstance();

	@FXML
	private TableColumn<ProdVO, String> prod_id;
	@FXML
	private TableColumn<ProdVO, String> prod_name;
	@FXML
	private TableColumn<ProdVO, String> prod_lgu;
	@FXML
	private TableColumn<ProdVO, String> prod_buyer;
	@FXML
	private TableColumn<ProdVO, String> prod_cost;
	@FXML
	private TableColumn<ProdVO, String> prod_price;
	@FXML
	private TableColumn<ProdVO, String> prod_sale;
	@FXML
	private TableColumn<ProdVO, String> prod_outline;
	@FXML
	private TableColumn<ProdVO, String> prod_detail;

	@FXML
	TableView<ProdVO> tv;
	@FXML
	ComboBox<String> Cb1;
	@FXML
	ComboBox<String> Cb2;

	ObservableList<String> cblist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prod_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
		prod_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
		prod_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		prod_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
		prod_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
		prod_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));

		ObservableList<String> list = FXCollections.observableArrayList("컴퓨터제품", "전자제품", "여성캐주얼", "남성캐주얼", "피혁잡화",
				"화장품", "음반/cd", "도서", "문구류");
		Cb1.setItems(list);
		Cb1.setValue(list.get(0));

		start();

		Cb1.setOnAction(e -> {
			cblist = FXCollections.observableArrayList();
			ObservableList<ProdVO> data = FXCollections.observableArrayList();

			String prodValueNum = MethodProdValueNum();
			addCombo(prodValueNum);
			Cb2.setItems(cblist);
			Cb2.setValue(cblist.get(0));

			ArrayList<ProdVO> list2 = new ArrayList<>();
			list2 = (ArrayList<ProdVO>) service.getProd2(Cb2.getValue());
			data = FXCollections.observableArrayList(list2);
			tv.setItems(data);
		});

		Cb2.setOnAction(e -> {
			ObservableList<ProdVO> data = FXCollections.observableArrayList();

			ArrayList<ProdVO> list2 = new ArrayList<>();
			list2 = (ArrayList<ProdVO>) service.getProd2(Cb2.getValue());
			data = FXCollections.observableArrayList(list2);
			tv.setItems(data);
		});
	}

	private void start() {
		cblist = FXCollections.observableArrayList();
		ObservableList<ProdVO> data = FXCollections.observableArrayList();

		String prodValueNum = MethodProdValueNum();
		addCombo(prodValueNum);
		Cb2.setItems(cblist);
		Cb2.setValue(cblist.get(0));

		ArrayList<ProdVO> list2 = new ArrayList<>();
		list2 = (ArrayList<ProdVO>) service.getProd2(Cb2.getValue());
		data = FXCollections.observableArrayList(list2);
		tv.setItems(data);
	}

	private void addCombo(String prodValueNum) {
		List<ProdVO> list = service.getProd1(prodValueNum);

		for (int i = 0; i < list.size(); i++) {
			cblist.add(list.get(i).getProd_name());
		}
	}

	private String MethodProdValueNum() {
		
		String prodValue = Cb1.getValue();
		
		String prodValueNum = "";
		
		if (prodValue.equals("컴퓨터제품")) {
			prodValueNum = "P101";
		
		} else if (prodValue.equals("전자제품")) {
			prodValueNum = "P102";
		
		} else if (prodValue.equals("여성캐주얼")) {
			prodValueNum = "P201";
		
		} else if (prodValue.equals("남성캐주얼")) {
			prodValueNum = "P202";
		
		} else if (prodValue.equals("피혁잡화")) {
			prodValueNum = "P301";
		
		} else if (prodValue.equals("화장품")) {
			prodValueNum = "P302";
		
		} 
		
		return prodValueNum;
	}
}
