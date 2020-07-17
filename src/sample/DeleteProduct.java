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

public class DeleteProduct {

    @FXML
    ComboBox comboProductIDs;
    public String silectedID;
    public static String IDSearching;

    public void initialize()
    {                                                                   //happen automatically, without calling
        ObservableList comboCategoryList= FXCollections.observableArrayList();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch=databaseInitialize.inventoryDb.getCollection("Product_Collection");
        DBCursor searching = categorySearch.find();

        for(DBObject count : searching)
        {
            IDSearching=(String)count.get("ProductID");
            comboCategoryList.add(IDSearching);

        } comboProductIDs.setItems(comboCategoryList);

    } public void method1Delete(){               ////delete method by using dropbox
        silectedID=(String) comboProductIDs.getValue();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Product_Collection");
        DBCursor searching = categorySearch.find();


        BasicDBObject catogerytoDelete = new BasicDBObject();
        catogerytoDelete.put("ProductID", silectedID);
        categorySearch.findAndRemove(catogerytoDelete);

        Alert successful = new Alert(Alert.AlertType.NONE);
        successful.setAlertType(Alert.AlertType.INFORMATION);
        successful.setContentText("Category Deleted Successfully ");
        successful.showAndWait();

        initialize();
    }
    public void methodBack(){
        Home.DeleteProductStage.close();
    }

}
