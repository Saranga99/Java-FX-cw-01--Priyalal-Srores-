package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllStock <findIterable> implements Initializable {
    @FXML
    private TableView<ModelClassViewAllStock> stockAllTblView;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> productIdTblColumn;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> productAmountTblColumn;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> unitTblColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productIdTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        productAmountTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductAmount"));
        unitTblColumn.setCellValueFactory(new PropertyValueFactory<>("Unit"));

        displayInTable();

    }
    public void displayInTable(){
        ObservableList<ModelClassViewAllStock> tableValueList = FXCollections.observableArrayList();
        databaseInitialize.methodDatabaseInt();
        DBCollection Check = databaseInitialize.inventoryDb.getCollection("Stock_Collection");
        DBCursor searching = Check.find();

        for (DBObject count : searching) {
            ModelClassViewAllStock tableValues = new ModelClassViewAllStock ();
            tableValues.setProductID((String) count.get("ProductID"));
            tableValues.setProductAmount((String) count.get("ProductAmount"));
            tableValues.setUnit((String)count.get("Unit"));
            tableValueList.add(tableValues);





        }
        stockAllTblView.setItems(tableValueList);
    }
    public void methodBack(){
        Home.ViewAllStockStage.close();
    }




}


