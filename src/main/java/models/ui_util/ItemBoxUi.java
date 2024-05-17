package models.ui_util;

public class ItemBoxUi {
    private String name;
    private String sub_name;
    private String photo_url;
    private String action_url;

    public ItemBoxUi() {
    }

    public ItemBoxUi(String name, String sub_name, String photo_url, String action_url) {
        this.name = name;
        this.sub_name = sub_name;
        this.photo_url = photo_url;
        this.action_url = action_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getAction_url() {
        return action_url;
    }

    public void setAction_url(String action_url) {
        this.action_url = action_url;
    }
}
