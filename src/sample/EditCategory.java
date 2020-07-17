package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditCategory
{
    @FXML
    TextField txtExistingCategory;
    @FXML
    TextField txtNewCategory;
    public static String categorySearching;

    public void clickBtnUpdate()
    {
        String categoryExist = txtExistingCategory.getText();
        categoryExist = categoryExist.toLowerCase();
        String categoryUpdate=txtNewCategory.getText();
        categoryUpdate=categoryUpdate.toLowerCase();
        Boolean categoryhere = true;

        if (!categoryExist.equals("") && !categoryUpdate.equals(""))
        {       databaseInitialize.methodDatabaseInt();
                DBCollection categorySearch = databaseInitialize.inventoryDb.getCollection("Category_Collection");
                DBCursor searching = categorySearch.find();


            for (DBObject count : searching)
            {
                categorySearching = (String) count.get("CategoryName");

                if (categoryExist.equals(categorySearching))
                {
                    categoryhere = false;
                    break;

                }else{
                    categoryhere = true;
                }
            }

                if (categoryhere)
                {
                    Alert alreadyExistsError = new Alert(Alert.AlertType.NONE);
                    alreadyExistsError.setAlertType(Alert.AlertType.ERROR);
                    alreadyExistsError.setContentText("Category doesn't  Exists to update");
                    alreadyExistsError.showAndWait();

                } else{

                    for (DBObject count : searching)
                    {
                        categorySearching = (String) count.get("CategoryName");

                        if (categoryUpdate.equals(categorySearching))
                        {
                            categoryhere = false;
                            break;

                        }else{
                            categoryhere = true;
                        }
                    }

                    if(categoryhere)
                    {
                        BasicDBObject updatingCategory = new BasicDBObject();
                        updatingCategory.put("CategoryName", categoryExist);
                        BasicDBObject updateValue = new BasicDBObject();
                        updateValue.put("CategoryName", categoryUpdate);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("$set",updateValue);
                        databaseInitialize.inventoryDb.getCollection("Category_Collection").update(updatingCategory, newValue);

                        Alert updateInfo = new Alert(Alert.AlertType.NONE);
                        updateInfo.setAlertType(Alert.AlertType.INFORMATION);
                        updateInfo.setContentText("Category updated Successfully");
                        updateInfo.showAndWait();
                    }else{
                        Alert alreadyError = new Alert(Alert.AlertType.NONE);
                        alreadyError.setAlertType(Alert.AlertType.ERROR);
                        alreadyError.setContentText(" Can't Update..New Value Already added as a Category");
                        alreadyError.showAndWait();

                    }
                }
        }else{
            Alert nullError = new Alert(Alert.AlertType.NONE);
            nullError.setAlertType(Alert.AlertType.ERROR);
            nullError.setContentText("Category Exist field or Category Update field is empty");
            nullError.showAndWait();
        }
    }
    public void methodBack(){
        Home.EditCategoryStage.close();
    }
}










