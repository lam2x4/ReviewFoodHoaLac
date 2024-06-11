package entity;

public class Comment {

    private int id;
    private int user_id;
    private String username;
    private int blog_id;
    private String content;
    private String create_date;
    private int likes;
    private int is_banned;

    public Comment() {
    }

    public Comment(int user_id, String username, int blog_id, String content, String create_date, int likes, int is_banned) {
        this.user_id = user_id;
        this.username = username;
        this.blog_id = blog_id;
        this.content = content;
        this.create_date = create_date;
        this.likes = likes;
        this.is_banned = is_banned;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
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

    public String getCreate_date() {
        return create_date;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", username=" + username + ", blog_id=" + blog_id + ", content=" + content + ", create_date=" + create_date + ", likes=" + likes + ", is_banned=" + is_banned + '}';
    }
}
