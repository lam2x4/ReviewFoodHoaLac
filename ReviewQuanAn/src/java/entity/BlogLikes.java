package entity;

public class BlogLikes {
    private int user_id;
    private int blog_id;
    private String interaction_type;

    public BlogLikes() {
    }

    public BlogLikes(int user_id, int blog_id, String interaction_type) {
        this.user_id = user_id;
        this.blog_id = blog_id;
        this.interaction_type = interaction_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getInteraction_type() {
        return interaction_type;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setInteraction_type(String interaction_type) {
        this.interaction_type = interaction_type;
    }

    @Override
    public String toString() {
        return "BlogLikes{" + "user_id=" + user_id + ", blog_id=" + blog_id + ", interaction_type=" + interaction_type + '}';
    }
}
