package sample;

import javafx.beans.property.SimpleStringProperty;

public class ModelClassProduct {
 private String ProductID;
 private String ProductName;
 private String CategoryNAME;

 public String getProductID(){return ProductID ;}
 public void setProductID(String ProductID){this.ProductID=ProductID; }

    public String getProductName(){return ProductName ;}
    public void setProductName(String ProductName){this.ProductName=ProductName; }


    public String getCategoryNAME(){return CategoryNAME ;}
    public void setCategoryNAME(String CategoryNAME){this.CategoryNAME=CategoryNAME;}


    
}
