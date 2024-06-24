package entity;

public class CommentLikes {
    private int blog_id;
    private int user_id;
    private int comment_id;
    private String interaction_type;

    public CommentLikes() {
    }

    public CommentLikes(int blog_id, int user_id, int comment_id, String interaction_type) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.comment_id = comment_id;
        this.interaction_type = interaction_type;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getInteraction_type() {
        return interaction_type;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setInteraction_type(String interaction_type) {
        this.interaction_type = interaction_type;
    }

    @Override
    public String toString() {
        return "CommentLikes{" + "blog_id=" + blog_id + ", user_id=" + user_id + ", comment_id=" + comment_id + ", interaction_type=" + interaction_type + '}';
    }
}
