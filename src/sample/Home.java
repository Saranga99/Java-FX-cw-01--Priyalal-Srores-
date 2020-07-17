package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home {
    public static Stage AddCategoryStage = new Stage();
    public static Stage EditCategoryStage = new Stage();
    public static Stage ViewCategoryStage = new Stage();
    public static Stage DeleteCategoryStage = new Stage();

    public static Stage AddProductStage = new Stage();
    public static Stage SearchProductStage = new Stage();
    public static Stage DeleteProductStage = new Stage();
    public static Stage ViewProductStage = new Stage();
    public static Stage EditProductStage = new Stage();

    public static Stage AddtoStockStage = new Stage();
    public static Stage RemovefromStockStage = new Stage();
    public static Stage ViewAllStockStage = new Stage();
    public static Stage ViewStockStage = new Stage();
    public static Stage AboutStage = new Stage();

    public static Stage PrimaryStage = new Stage();


    public void methodClickAdd() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        AddCategoryStage.setTitle("Add Category");
        AddCategoryStage.setScene(new Scene(root, 550, 400));
        AddCategoryStage.show();
    }

    public void methodClickEdit() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        EditCategoryStage.setTitle("Edit Category");
        EditCategoryStage.setScene(new Scene(root, 550, 400));
        EditCategoryStage.show();
    }

    public void methodClickView() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewCategory.fxml"));
        ViewCategoryStage.setTitle("View Category");
        ViewCategoryStage.setScene(new Scene(root, 550, 400));
        ViewCategoryStage.show();
    }

    public void methodClickDelete() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DeleteCategory.fxml"));
        DeleteCategoryStage.setTitle("Delete Category");
        DeleteCategoryStage.setScene(new Scene(root, 550, 400));
        DeleteCategoryStage.show();
    }

    public void methodClickAddProduct() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        AddProductStage.setTitle("Add Product");
        AddProductStage.setScene(new Scene(root, 550, 400));
        AddProductStage.show();
    }

    public void methodClickSearchProduct() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SearchProduct.fxml"));
        SearchProductStage.setTitle("Search Product");
        SearchProductStage.setScene(new Scene(root, 550, 400));
        SearchProductStage.show();
    }

    public void methodClickDeleteProduct() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DeleteProduct.fxml"));
        DeleteProductStage.setTitle("Delete Product");
        DeleteProductStage.setScene(new Scene(root, 550, 400));
        DeleteProductStage.show();
    }

    public void methodClickViewProduct() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewProduct.fxml"));
        ViewProductStage.setTitle("View Product");
        ViewProductStage.setScene(new Scene(root, 550, 400));
        ViewProductStage.show();
    }

    public void methodClickEditProduct() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        EditProductStage.setTitle("Edit Product");
        EditProductStage.setScene(new Scene(root, 550, 400));
        EditProductStage.show();
    }

    public void methodClickAddtoStock() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("AddtoStock.fxml"));
        AddtoStockStage.setTitle("Add to Stock");
        AddtoStockStage.setScene(new Scene(root, 550, 400));
        AddtoStockStage.show();
    }

    public void methodClickRemovefromStock() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("RemovefromStock.fxml"));
        RemovefromStockStage.setTitle("Remove from Stock");
        RemovefromStockStage.setScene(new Scene(root, 550, 400));
        RemovefromStockStage.show();
    }

    public void methodClickViewAllStock() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewAllStock.fxml"));
        ViewAllStockStage.setTitle("View Stock  (All)");
        ViewAllStockStage.setScene(new Scene(root, 550, 400));
        ViewAllStockStage.show();
    }

    public void methodClickViewStock() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewStock.fxml"));
        ViewStockStage.setTitle("View Stock (One)");
        ViewStockStage.setScene(new Scene(root, 550, 400));
        ViewStockStage.show();
    }

    public void methodClickAbout() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        AboutStage.setTitle("About");
        AboutStage.setScene(new Scene(root, 550, 400));
        AboutStage.show();
    }

    public void methodClickBtnLogout() throws Exception {

        Main.PrimaryStage.show();
        Login.homeStage.close();
    }
}