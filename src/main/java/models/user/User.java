package models.user;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class User {

    public User(Document document){
        this.id = document.getObjectId("_id");
        this.name = document.getString("name");
        this.username = document.getString("username");
    }
    private ObjectId id;
    @BsonProperty(value = "username")
    private String username;
    @BsonProperty(value = "name")
    private String name;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
