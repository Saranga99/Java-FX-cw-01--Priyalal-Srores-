package sample;


import com.mongodb.DB;
import com.mongodb.MongoClient;

public class databaseInitialize {

    public static DB inventoryDb;
    public static MongoClient mongoClient = new MongoClient("localhost", 27017);


    public static void methodDatabaseInt() {
        inventoryDb = mongoClient.getDB("Inventory_Db");
        inventoryDb.createCollection("Category_Collection", null);
        inventoryDb.createCollection("Product_Collection", null);
        inventoryDb.createCollection("Stock_Collection", null);
    }


}
