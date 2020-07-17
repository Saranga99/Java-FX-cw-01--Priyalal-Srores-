package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddCategory {
    @FXML
    TextField txtCategory;
    public static String categorySearching;

    public void clickBtnAdd(){
        Boolean categoryhere=true;
        String category=txtCategory.getText();
        category=category.toLowerCase();
        if(!category.equals("")){

           databaseInitialize.methodDatabaseInt();
           DBCollection categorySearch=databaseInitialize.inventoryDb.getCollection("Category_Collection");
           DBCursor searching = categorySearch.find();

           for(DBObject count : searching){
             categorySearching=(String)count.get("CategoryName");
             if(category.equals(categorySearching)){
                 categoryhere=false;
             }else{ categoryhere=true; }
           }
                if(!categoryhere){
                    Alert alreadyExistsError = new Alert(Alert.AlertType.NONE);
                    alreadyExistsError.setAlertType(Alert.AlertType.ERROR);
                    alreadyExistsError.setContentText("Category already Exists");
                    alreadyExistsError.showAndWait();

                }else{
                    BasicDBObject categorytoAdd = new BasicDBObject();
                    categorytoAdd.put("CategoryName", category);
                    databaseInitialize.methodDatabaseInt();
                    DBCollection collection = databaseInitialize.inventoryDb.getCollection("Category_Collection");
                    collection.insert(categorytoAdd);

                    Alert successful = new Alert(Alert.AlertType.NONE);
                    successful.setAlertType(Alert.AlertType.INFORMATION);
                    successful.setContentText("Category added Successfully ");
                    successful.showAndWait();
                }
        }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Category field is empty");
            nullError.showAndWait();

       }
    }
    public void methodBack(){
        Home.AddCategoryStage.close();
    }

}