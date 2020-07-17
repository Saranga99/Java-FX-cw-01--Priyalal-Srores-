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

public class DeleteCategory
{       @FXML
        ComboBox comboCategories;
        public String silectedCategory;

        public static String categorySearching;

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

    public void method1Delete(){               ////delete method by using dropbox
        silectedCategory=(String) comboCategories.getValue();

        databaseInitialize.methodDatabaseInt();
        DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Category_Collection");
        DBCursor searching = categorySearch.find();


        BasicDBObject catogerytoDelete = new BasicDBObject();
        catogerytoDelete.put("CategoryName", silectedCategory);
        categorySearch.findAndRemove(catogerytoDelete);

        Alert successful = new Alert(Alert.AlertType.NONE);
        successful.setAlertType(Alert.AlertType.INFORMATION);
        successful.setContentText("Category Deleted Successfully ");
        successful.showAndWait();
        initialize();
    }




    @FXML
    TextField txtDeleteCategory;


    @FXML
    public void method2Delete()       //if you want type category and delete this method is also working
    {
        Boolean categoryhere = true;
        String categorytoDelete = txtDeleteCategory.getText();
        categorytoDelete = categorytoDelete.toLowerCase();
        if (!categorytoDelete.equals(""))
        {

            databaseInitialize.methodDatabaseInt();
            DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Category_Collection");
            DBCursor searching = categorySearch.find();

            for (DBObject count : searching)
            {
                categorySearching = (String) count.get("CategoryName");

                if (categorytoDelete.equals(categorySearching))
                {
                    categoryhere = false;
                    break;
                } else{
                    continue;
                }
            }

            if (!categoryhere)
            {
                BasicDBObject catogerytoDelete = new BasicDBObject();
                catogerytoDelete.put("CategoryName", categorytoDelete);
                categorySearch.findAndRemove(catogerytoDelete);

                Alert successful = new Alert(Alert.AlertType.NONE);
                successful.setAlertType(Alert.AlertType.INFORMATION);
                successful.setContentText("Category Deleted Successfully ");
                successful.showAndWait();
            } else {
                Alert alreadyExistsError = new Alert(Alert.AlertType.NONE);
                alreadyExistsError.setAlertType(Alert.AlertType.ERROR);
                alreadyExistsError.setContentText("Category not found in Database");
                alreadyExistsError.showAndWait();
            }


        } else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Category field is empty");
            nullError.showAndWait();

        }
    }
    public void methodBack(){
        Home.DeleteCategoryStage.close();
    }

}


