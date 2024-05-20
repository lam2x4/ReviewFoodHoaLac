package entity;

public class Post {
    private int customer_id;
    private int store_id;
    private boolean is_banned;
    private String content;

    public Post() {
    }

    public Post(int customer_id, int store_id, String content) {
        this.customer_id = customer_id;
        this.store_id = store_id;
        is_banned = false;
        this.content = content;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public String getContent() {
        return content;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" + "customer_id=" + customer_id + ", store_id=" + store_id + ", is_banned=" + is_banned + ", content=" + content + '}';
    }
    
    
}
