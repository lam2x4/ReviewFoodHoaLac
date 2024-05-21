package entity;

public class Comment {
    private int user_id;
    private int store_id;
    private String content;
    private int rating;
    private int likes;

    public Comment() {
    }

    public Comment(int user_id, int store_id, String content, int rating) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.content = content;
        this.rating = rating;
        likes = 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public int getLikes() {
        return likes;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" + "user_id=" + user_id + ", store_id=" + store_id + ", content=" + content + ", rating=" + rating + ", likes=" + likes + '}';
    }
    
    
}
