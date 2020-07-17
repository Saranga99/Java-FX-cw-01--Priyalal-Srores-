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

public class EditProduct {
    @FXML
    ComboBox comboProductID;
    @FXML
    ComboBox comboCategories;
    @FXML
    TextField txtIdValue;
    @FXML
    TextField txtNameValue;
    public  String IDSearching;
    public  String CategorySearching;
    public  String oldID;
    public  String categoryValue;
    public  String product;
    public  String category;
    public  String proName;
    public  String categoryname;


    public void initialize(){                                                                   //happen automatically, without calling
        ObservableList comboIDList = FXCollections.observableArrayList();//get all IDs in collection to the Combobox

        databaseInitialize.methodDatabaseInt();
        DBCollection idSearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
        DBCursor idsearching = idSearch.find();
        for (DBObject count : idsearching) {
            IDSearching = (String) count.get("ProductID");
            comboIDList.add(IDSearching);
        }comboProductID.setItems(comboIDList);

        ObservableList comboCategoryList = FXCollections.observableArrayList();  //get all categories in collection to the Combobox

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Category_Collection");
        DBCursor categorySearching = categorySearch.find();
        for (DBObject count : categorySearching) {
            CategorySearching = (String) count.get("CategoryName");
            comboCategoryList.add(CategorySearching);
        }comboCategories.setItems(comboCategoryList);
    }


    public void clickBtnUpdate(){
        oldID=(String) comboProductID.getValue();
        categoryValue=(String) comboCategories.getValue();
        String newID=txtIdValue.getText();
        String newProductName=txtNameValue.getText();
        newProductName=newProductName.toLowerCase();
        Boolean productIDhere=true;

        if(oldID!="null"&&categoryValue!="null"&&!newID.equals("")&&!newProductName.equals("")){
           if(newID.length()>5){
               Alert idLengthError = new Alert(Alert.AlertType.NONE);
               idLengthError.setAlertType(Alert.AlertType.ERROR);
               idLengthError.setContentText("ERROR...Product ID must be 5 characters or less than 5  ");
               idLengthError.showAndWait();
           }else{
               databaseInitialize.methodDatabaseInt();     //ID
               DBCollection idSearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
               DBCursor searchingID = idSearch.find();


               for (DBObject count : searchingID) {
                   IDSearching = (String) count.get("ProductID");
                   proName = (String) count.get("ProductName");
                   categoryname = (String) count.get("CategoryNAME");


                   if (newID.equals(IDSearching)) {


                       productIDhere = false;
                       break;
                   } else {

                       productIDhere = true;
                   }
               }

               if (productIDhere) {
                   BasicDBObject updatingProduct = new BasicDBObject();
                   updatingProduct.put("ProductID", oldID);
                   updatingProduct.put("ProductName", proName);
                   updatingProduct.put("CategoryNAME", categoryname);

                   BasicDBObject updateValue = new BasicDBObject();
                   updateValue.put("ProductID", newID);
                   updateValue.put("ProductName", newProductName);
                   updateValue.put("CategoryNAME", categoryValue);
                   BasicDBObject newValue = new BasicDBObject();
                   newValue.put("$set", updateValue);
                   databaseInitialize.inventoryDb.getCollection("Product_Collection").update(updatingProduct, newValue);

                   Alert successful = new Alert(Alert.AlertType.NONE);
                   successful.setAlertType(Alert.AlertType.INFORMATION);
                   successful.setContentText("Product Updated Successfully ");
                   successful.showAndWait();
                   initialize();



               } else {
                   Alert IdAlreadyError = new Alert(Alert.AlertType.NONE);
                   IdAlreadyError.setAlertType(Alert.AlertType.ERROR);
                   IdAlreadyError.setContentText("Given Product ID is already in Database");
                   IdAlreadyError.showAndWait();
               }
           }

        }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("ERROR...Every fields must be filled and Selected");
            nullError.showAndWait();
        }
    }
    public void methodBack(){
        Home.EditProductStage.close();
    }
}
