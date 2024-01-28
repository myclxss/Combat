package myclass.plugin.combat.manager.Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseConection {


    public MongoClient mongoClient;
    public MongoDatabase database;
    public MongoCollection<Document> collection;

    public void conectDB() {
        String connectionString = "mongodb+srv://myclassCombat:CMO80vdbMmRIY5Zp@cluster0.yoexgvj.mongodb.net/";
        MongoClientURI uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);

        database = mongoClient.getDatabase("Combat");
        collection = database.getCollection("user");
    }

    public void closeDB() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

}
