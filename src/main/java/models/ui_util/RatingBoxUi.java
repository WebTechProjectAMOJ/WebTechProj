package models.ui_util;

public class RatingBoxUi extends ItemBoxUi {
    private Integer rating;
    private String content;

    public RatingBoxUi(String name, String sub_name, String photo_url, String action_url, Integer rating, String content) {
        super(name, sub_name, photo_url, action_url, "rating");
        this.rating = rating;
        this.content = content;
    }

    public RatingBoxUi() {
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
