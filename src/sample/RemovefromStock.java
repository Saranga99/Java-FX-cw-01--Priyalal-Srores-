package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RemovefromStock
{
    @FXML
    ComboBox comboProductIDs;
    @FXML
    ComboBox comboUnits;
    @FXML
    TextField txtproductAmount;
    public  String IDSearching;
    public  String availableAmount;
    public String silectedID;
    public String unit;
    public Integer amount;
    public Integer newAmnt;
    public String availabUnit;






    public void initialize()
    {                                                                   //happen automatically, without calling
        ObservableList comboCategoryList= FXCollections.observableArrayList();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch=databaseInitialize.inventoryDb.getCollection("Stock_Collection");
        DBCursor searching = categorySearch.find();

        for(DBObject count : searching)
        {
            IDSearching=(String)count.get("ProductID");
            comboCategoryList.add(IDSearching);

        } comboProductIDs.setItems(comboCategoryList);


        ObservableList unitList=FXCollections.observableArrayList();
        unitList.add("Kg");
        unitList.add("L");
        unitList.add("");
        comboUnits.setItems(unitList);

    }

    public void clickRemovefromStock(){
        silectedID = (String) comboProductIDs.getValue();
        unit = (String) comboUnits.getValue();
        String productAmount = txtproductAmount.getText();
        Boolean idhere = true;


        if (!productAmount.equals("") && silectedID != null){

            databaseInitialize.methodDatabaseInt();
            DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Stock_Collection");
            DBCursor searching = categorySearch.find();

            for (DBObject count : searching){
                IDSearching = (String) count.get("ProductID");
                availableAmount = (String) count.get("ProductAmount");
                availabUnit = (String) count.get("Unit");

                if(silectedID.equals(IDSearching)){
                    idhere = false;
                    break;

                }else{
                    idhere = true;
                }
            }
            if (idhere){
                BasicDBObject docStock = new BasicDBObject();
                docStock.put("ProductID", silectedID);
                docStock.put("ProductAmount", productAmount);
                docStock.put("Unit", unit);
                databaseInitialize.methodDatabaseInt();
                DBCollection collection = databaseInitialize.inventoryDb.getCollection("Stock_Collection");
                collection.insert(docStock);

                Alert successful = new Alert(Alert.AlertType.NONE);
                successful.setAlertType(Alert.AlertType.INFORMATION);
                successful.setContentText("Stock added Successfully ");
                successful.showAndWait();

            }else{
                int productAmnt = Integer.parseInt(productAmount);
                int availableAmnt = Integer.parseInt(availableAmount);
                newAmnt = availableAmnt - productAmnt;
                String finalValue = String.valueOf(newAmnt);

                BasicDBObject updatingAmount = new BasicDBObject();
                updatingAmount.put("ProductID", silectedID);
                updatingAmount.put("ProductAmount", availableAmount);
                updatingAmount.put("Unit", availabUnit);

                BasicDBObject updateValue = new BasicDBObject();
                updateValue.put("ProductID", silectedID);
                updateValue.put("ProductAmount", finalValue);
                updateValue.put("Unit", availabUnit);
                BasicDBObject newValue = new BasicDBObject();
                newValue.put("$set", updateValue);
                databaseInitialize.inventoryDb.getCollection("Stock_Collection").update(updatingAmount, newValue);

                Alert successful = new Alert(Alert.AlertType.NONE);
                successful.setAlertType(Alert.AlertType.INFORMATION);
                successful.setContentText("Stock Removed Successfully ");
                successful.showAndWait();
            }
        }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("ERROR...ID is not selected or amount isn't filled ");
            nullError.showAndWait();
        }
    }
    public void methodBack(){
        Home.RemovefromStockStage.close();
    }
}
