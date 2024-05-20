package entity;

public class Item {
    private int store_id;
    private int id;
    private String name;
    private int rating; //float?

    public Item() {
    }

    public Item(int store_id, int id, String name, int rating) {
        this.store_id = store_id;
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public int getStore_id() {
        return store_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Item{" + "store_id=" + store_id + ", id=" + id + ", name=" + name + ", rating=" + rating + '}';
    }
    
    
}
