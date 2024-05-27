package entity;

public class Blog {
    private int id;
    private int user_id;
    private String title;
    private String content;
    private int likes;

    public Blog() {
    }

    public Blog(int user_id, String title, String content, int likes) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", user_id=" + user_id + ", title=" + title + ", content=" + content + ", likes=" + likes + '}';
    }

    
}
