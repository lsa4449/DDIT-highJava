package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ComboGugudanController implements Initializable {

   @FXML 
   private ComboBox<Integer> cmbDan;
   @FXML 
   private TextArea txtResult;
   @FXML 
   private Button btnDan;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      
      assert cmbDan != null : "fx:id\"cmbDan\" was not injected: "
                        + "check your FXML file 'ComboGugudan.fxml.";
      
      assert btnDan != null : "fx:id\"btnDan\" was not injected: "
            + "check your FXML file 'ComboGugudan.fxml.";
      
      assert txtResult != null : "fx:id\"txtResult\" was not injected: "
            + "check your FXML file 'ComboGugudan.fxml.";
      
      ObservableList<Integer> list = 
            FXCollections.observableArrayList(
              1,2,3,4,5,6,7,8,9
               );
         cmbDan.setItems(list);
         
         btnDan.setOnAction(e->{
            //int dan = cmbDan.getSelectionModel().getSelectedItem();
            int dan = cmbDan.getValue();
            txtResult.setText(dan + "단\n\n");
            for(int i=1; i<=9; i++) {
               int r = dan * i;
               txtResult.appendText(dan + " * " + i + " = " + r + "\n");
            }
         });
      
   }

   @FXML public void btnDanClicked(ActionEvent event) {
      //int dan = cmbDan.getSelectionModel().getSelectedItem();
//      int dan = cmbDan.getValue();
//      txtResult.setText(dan + "단\n\n");
//      for(int i=1; i<=9; i++) {
//         int r = dan * i;
//         txtResult.appendText(dan + " * " + i + " = " + r + "\n");
//      }
   }
   
}