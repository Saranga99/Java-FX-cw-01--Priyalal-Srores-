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

public class AddProduct
{   @FXML
    ComboBox comboCategories;
    @FXML
    TextField txtProductID;
    @FXML
    TextField txtProductName;

    public String categorySearching;
    public String silectedCategory;




    public void initialize()
    {                                                                   //happen automatically, without calling
        ObservableList comboCategoryList= FXCollections.observableArrayList();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch=databaseInitialize.inventoryDb.getCollection("Category_Collection");
        DBCursor searching = categorySearch.find();

        for(DBObject count : searching)
        {
            categorySearching=(String)count.get("CategoryName");
            comboCategoryList.add(categorySearching);

        } comboCategories.setItems(comboCategoryList);

    }

    public void methodAddProduct()
    {
        Boolean productIDhere=true;


        silectedCategory=(String) comboCategories.getValue();
        //System.out.println(silectedCategory);
        String productID=txtProductID.getText();
        String productName=txtProductName.getText();
        productName=productName.toLowerCase();
        if(productID.equals("")||productName.equals("")||silectedCategory==null){
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("ERROR...Every fields must be filled and selected");
            nullError.showAndWait();

        }else if(productID.length()>5){
            Alert idLengthError = new Alert(Alert.AlertType.NONE);
            idLengthError.setAlertType(Alert.AlertType.ERROR);
            idLengthError.setContentText("ERROR...Product ID must be 5 characters or less than 5  ");
            idLengthError.showAndWait();



        } else {
                databaseInitialize.methodDatabaseInt();     //ID
                DBCollection idSearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
                DBCursor searchingID = idSearch.find();

                for (DBObject count : searchingID) {
                    categorySearching = (String) count.get("ProductID");

                    if (productID.equals(categorySearching)) {
                        productIDhere = false;
                        break;
                    } else {
                        productIDhere = true;
                    }


                }
                if (productIDhere) {
                    BasicDBObject docProduct = new BasicDBObject();
                    docProduct.put("ProductID", productID);
                    docProduct.put("ProductName", productName);
                    docProduct.put("CategoryNAME", silectedCategory);
                    databaseInitialize.methodDatabaseInt();
                    DBCollection collection = databaseInitialize.inventoryDb.getCollection("Product_Collection");
                    collection.insert(docProduct);
                    System.out.println("done");

                    Alert successful = new Alert(Alert.AlertType.NONE);
                    successful.setAlertType(Alert.AlertType.INFORMATION);
                    successful.setContentText("Product added Successfully ");
                    successful.showAndWait();


                } else {
                    Alert IdAlreadyError = new Alert(Alert.AlertType.NONE);
                    IdAlreadyError.setAlertType(Alert.AlertType.ERROR);
                    IdAlreadyError.setContentText("Given Product ID is already in Database");
                    IdAlreadyError.showAndWait();

                }
        }
    }

    public void methodBack(){
        Home.AddProductStage.close();
    }
}




