package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class SearchProduct {
    @FXML
    TextField txtProductID;
    @FXML
    TextField txtProductName;
    public String productSearching;



    public void searchWithID(){
        String valueID=txtProductID.getText();
        Boolean productIDhere=true;

        if(valueID.equals("")){
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Search ID field is empty");
            nullError.showAndWait();

        }else if (valueID.length()<=5){
            databaseInitialize.methodDatabaseInt();     //ID
            DBCollection idSearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
            DBCursor searchingID = idSearch.find();

            for (DBObject count : searchingID) {
                productSearching = (String) count.get("ProductID");
                if (valueID.equals(productSearching)) {
                    productIDhere = false;
                    break;
                } else {
                    productIDhere = true;
                }
            }
            if(!productIDhere){
                Alert idAvailable = new Alert(Alert.AlertType.NONE);
                idAvailable.setAlertType(Alert.AlertType.INFORMATION);
                idAvailable.setContentText("Product is Available in Database ");
                idAvailable.showAndWait();

            }else{
                Alert idNotAvailable = new Alert(Alert.AlertType.NONE);
                idNotAvailable.setAlertType(Alert.AlertType.INFORMATION);
                idNotAvailable.setContentText("Product is not Available in Database ");
                idNotAvailable.showAndWait();
            }

        }else {
            Alert idLengthError = new Alert(Alert.AlertType.NONE);
            idLengthError.setAlertType(Alert.AlertType.ERROR);
            idLengthError.setContentText("ERROR...Product ID must be 5 characters or less than 5  ");
            idLengthError.showAndWait();
        }

    }

    public void searchWithName(){
        String valueName=txtProductName.getText();
        valueName=valueName.toLowerCase();
        Boolean prpductNamehere=true;

        if(!valueName.equals("")){

            databaseInitialize.methodDatabaseInt();     //Name
            DBCollection nameSearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
            DBCursor searchingName = nameSearch.find();

            for (DBObject count : searchingName) {
                productSearching = (String) count.get("ProductName");
                if (valueName.equals(productSearching)) {
                    prpductNamehere = false;
                    break;
                } else {
                    prpductNamehere = true;
                }
            }
            if(!prpductNamehere){
                Alert idAvailable = new Alert(Alert.AlertType.NONE);
                idAvailable.setAlertType(Alert.AlertType.INFORMATION);
                idAvailable.setContentText("Product is Available in Database ");
                idAvailable.showAndWait();

            }else{
                Alert idNotAvailable = new Alert(Alert.AlertType.NONE);
                idNotAvailable.setAlertType(Alert.AlertType.INFORMATION);
                idNotAvailable.setContentText("Product is not Available in Database ");
                idNotAvailable.showAndWait();
            }

        }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Search Name field is empty");
            nullError.showAndWait();
        }
    }
    public void methodBack(){
        Home.SearchProductStage.close();
    }
}

