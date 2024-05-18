package models.tags;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Tag {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "type")
    private String type;

    public Tag() {}

    public Tag(Document doc) {
        this.id = doc.getObjectId("_id");
        this.name = doc.getString("name");
        this.type = doc.getString("type");
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
