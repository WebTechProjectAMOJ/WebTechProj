package models.delivery;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Tool {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "type")
    private String type;

    public Tool(String name, String type) {
        setName(name);
        setType(type);
    }

    public Tool(Document document) {
        setName(document.getString("name"));
        setType(document.getString("type"));
        setId(document.getObjectId("_id"));
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

    public Document toDocument() {
        Document doc = new Document();
        doc.put("name", getName());
        doc.put("type", getType());
        return doc;
    }

    public boolean write(){
        Document doc = this.toDocument();
        InsertOneResult written = DbConnection.insertOne("tools",doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return written.wasAcknowledged();
    }
}
