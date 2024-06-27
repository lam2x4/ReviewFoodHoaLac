package entity;

public class Images {
    private int id;
    private int blog_id;
    private String link;

    public Images() {
    }

    public Images(int blog_id, String link) {
        this.blog_id = blog_id;
        this.link = "img/" + link;
    }

    public int getId() {
        return id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setLink(String link) {
        this.link = "img/" + link;
    }

    @Override
    public String toString() {
        return "Images{" + "id=" + id + ", blog_id=" + blog_id + ", link=" + link + '}';
    }

    
}
