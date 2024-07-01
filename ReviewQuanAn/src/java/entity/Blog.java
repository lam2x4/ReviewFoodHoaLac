package entity;

public class Blog {

    private int id;
    private int user_id;
    private String title;
    private String content;
    private String create_date;
    private int likes;
    private int is_approved;
    private int is_banned;
    private int author_id;
    private String reason_reject;

    public String getReason_reject() {
        return reason_reject;
    }

    public void setReason_reject(String reason_reject) {
        this.reason_reject = reason_reject;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    //ADDED BY TUAN ANH ---- FOR SEARCH FUNCTION
    private String username;

    public Blog(String username, int id, int user_id, String title, String content, String create_date, int likes, int is_approved, int is_banned) {
        this.username = username;
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.create_date = create_date;
        this.likes = likes;
        this.is_approved = is_approved;
        this.is_banned = is_banned;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // THIS CODE DOES NOT AFFECT ANY OTHER EXISTING FUNCTIONS
    public Blog() {
    }

    public Blog(int user_id, String title, String content, String create_date, int likes, int is_approved, int is_banned) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.create_date = create_date;
        this.likes = likes;
        this.is_approved = is_approved;
        this.is_banned = is_banned;
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

    public String getCreate_date() {
        return create_date;
    }

    public int getIs_approved() {
        return is_approved;
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

    public void setTitle(String title) {
        this.title = title;
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

    public void setIs_approved(int is_approved) {
        this.is_approved = is_approved;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", user_id=" + user_id + ", title=" + title + ", content=" + content + ", create_date=" + create_date + ", likes=" + likes + ", is_approved=" + is_approved + ", is_banned=" + is_banned + '}';
    }

}
