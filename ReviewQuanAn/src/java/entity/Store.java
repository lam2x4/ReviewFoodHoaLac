package entity;

public class Store {
    private int store_id;
    private String store_name;
    private int manager_id;
    private float avg_rating;

    public Store() {
    }

    public Store(int store_id, String store_name, int manager_id, float avg_rating) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.manager_id = manager_id;
        this.avg_rating = avg_rating;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public int getManager_id() {
        return manager_id;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    @Override
    public String toString() {
        return "Store{" + "store_id=" + store_id + ", store_name=" + store_name + ", manager_id=" + manager_id + ", avg_rating=" + avg_rating + '}';
    }
    
    
}
