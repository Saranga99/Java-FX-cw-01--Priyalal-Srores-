package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewStock <findIterable> implements Initializable
{
    @FXML
    private TableView<ModelClassViewAllStock> stockTblView;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> productIdTblColumn;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> productAmountTblColumn;
    @FXML
    public TableColumn<ModelClassViewAllStock,String> unitTblColumn;
    public String IDSearching;
    @FXML
    ComboBox comboProductIDs;
    public String IDValue;


    @Override
    public void initialize(URL location, ResourceBundle resources) //happen automatically, without calling
    {
        productIdTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        productAmountTblColumn.setCellValueFactory(new PropertyValueFactory<>("ProductAmount"));
        unitTblColumn.setCellValueFactory(new PropertyValueFactory<>("Unit"));

        ObservableList comboCategoryList= FXCollections.observableArrayList();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch=databaseInitialize.inventoryDb.getCollection("Stock_Collection");
        DBCursor searching = categorySearch.find();

        for(DBObject count : searching)
        {
            IDSearching=(String)count.get("ProductID");
            comboCategoryList.add(IDSearching);

        } comboProductIDs.setItems(comboCategoryList);

    }





    public void displayInTable()
    {

        IDValue=(String) comboProductIDs.getValue();
        if(IDValue!=null){


        ObservableList<ModelClassViewAllStock> tableValueSet = FXCollections.observableArrayList();
        databaseInitialize.methodDatabaseInt();
        DBCollection Check = databaseInitialize.inventoryDb.getCollection("Stock_Collection");
        DBCursor searching = Check.find();

        for (DBObject count : searching) {
            if (IDValue.equals(searching)) {
                ModelClassViewAllStock values = new ModelClassViewAllStock();
                values.setProductID((String) count.get(IDValue));
                values.setProductAmount((String) count.get("ProductAmount"));
                values.setUnit((String) count.get("Unit"));
                tableValueSet.add(values);
                System.out.print(tableValueSet);
                break;

            }else{continue;}
        }stockTblView.setItems(tableValueSet);  System.out.print(tableValueSet);
    }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("ERROR...ID is not selected ");
            nullError.showAndWait();
        }
    }
    public void methodBack(){
        Home.ViewStockStage.close();
    }
}




