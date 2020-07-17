package sample;

import javafx.beans.property.SimpleStringProperty;

public class ModelClassCategory {

    private SimpleStringProperty category;

    public ModelClassCategory(String category) {

        this.category = new SimpleStringProperty(category);
    }

    public String getCategory() {

        return category.get();
    }

    public void setCategory(String category) {

        this.category = new SimpleStringProperty(category);
    }



}
