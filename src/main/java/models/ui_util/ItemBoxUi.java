package models.ui_util;

import models.user.login;

public class ItemBoxUi {
    //* A class to be used to represent an Order/Restaurant/Food Item in the UI
    private String name;
    private String sub_name;
    private String photo_url;
    private String action_url;
    private login user;
    private String type;

    public ItemBoxUi() {
    }

    public ItemBoxUi(String name, String sub_name, String photo_url, String action_url, String type) {
        this.name = name;
        this.sub_name = sub_name;
        this.photo_url = photo_url;
        this.action_url = action_url;
        this.type = type;
    }

    public String getType() {
        return type;
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
