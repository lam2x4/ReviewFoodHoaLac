package entity;

public class Store {
    private int id;
    private String name;
    private boolean is_banned;
    private float average_rating;

    public Store() {
    }

    public Store(int id, String name, float average_rating) {
        this.id = id;
        this.name = name;
        is_banned = false;
        this.average_rating = average_rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public float getAverage_rating() {
        return average_rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public void setAverage_rating(float average_rating) {
        this.average_rating = average_rating;
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", is_banned=" + is_banned + ", average_rating=" + average_rating + '}';
    }
    
    
}
