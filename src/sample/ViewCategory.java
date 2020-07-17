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

public class ViewCategory <findIterable> implements Initializable {
    @FXML
    private TableView<ModelClassCategory> categoryTblView;
    @FXML
    public TableColumn<ModelClassCategory,String> categoryTblColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryTblColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        displayInTable();

    }
    public void displayInTable(){
        ObservableList<ModelClassCategory> categoryList = FXCollections.observableArrayList();
        databaseInitialize.methodDatabaseInt();
        DBCollection categoryCheck = databaseInitialize.inventoryDb.getCollection("Category_Collection");
        DBCursor searching = categoryCheck.find();
        for (DBObject count : searching) {
            ModelClassCategory category = new ModelClassCategory ((String) count.get("CategoryName"));
            category.setCategory((String) count.get("CategoryName"));
            categoryList.add(category);

        }
        categoryTblView.setItems(categoryList);
    }
    public void methodBack(){
        Home.ViewCategoryStage.close();
    }

}
