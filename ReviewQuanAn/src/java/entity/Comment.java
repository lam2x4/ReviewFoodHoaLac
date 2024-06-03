package entity;

public class Comment {
    private int id;
    private int user_id;
    private int blog_id;
    private String content;
    private int likes;

    public Comment() {
    }

    public Comment(int user_id, int blog_id, String content, int likes) {
        this.user_id = user_id;
        this.blog_id = blog_id;
        this.content = content;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", user_id=" + user_id + ", blog_id=" + blog_id + ", content=" + content + ", likes=" + likes + '}';
    }

    
}
