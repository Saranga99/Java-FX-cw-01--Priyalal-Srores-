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


public class ViewProduct <findIterable> implements Initializable {
    @FXML
    private TableView<ModelClassProduct> productTblView;
    @FXML
    public TableColumn<ModelClassProduct,String> categoryTblColumn;
    @FXML
    public TableColumn<ModelClassProduct,String> productIDTblColumn;
    @FXML
    public TableColumn<ModelClassProduct,String> productNameTblColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productIDTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        productNameTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        categoryTblColumn.setCellValueFactory(new PropertyValueFactory<>("CategoryNAME"));

        displayInTable();

    }
    public void displayInTable(){
        ObservableList<ModelClassProduct> tableValueList = FXCollections.observableArrayList();
        databaseInitialize.methodDatabaseInt();
        DBCollection categoryCheck = databaseInitialize.inventoryDb.getCollection("Product_Collection");
        DBCursor searching = categoryCheck.find();

        for (DBObject count : searching) {
            ModelClassProduct tableValues = new ModelClassProduct ();
            tableValues.setProductID((String) count.get("ProductID"));
            tableValues.setProductName((String) count.get("ProductName"));
            tableValues.setCategoryNAME((String)count.get("CategoryNAME"));
            tableValueList.add(tableValues);





        }
        productTblView.setItems(tableValueList);
    }
    public void methodBack(){
        Home.ViewProductStage.close();
    }

}