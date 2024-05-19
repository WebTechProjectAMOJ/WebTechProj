package models.user;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;


public class Credential {
    @BsonProperty(value = "password")
    private String password;
    @BsonProperty(value = "username")
    private String username;

    public Credential(Document credentials) {
        setPassword((String) credentials.getString("password"));
        setUsername((String) credentials.getString("username"));
    }

    public Credential(String username, String password) {
        setPassword(password);
        setUsername(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.put("password", password);
        doc.put("username", username);
        return doc;
    }
}
