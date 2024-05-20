package entity;

public class ItemRating {
    private int customer_id;
    private int store_id;
    private int item_id;
    private int value;

    public ItemRating() {
    }

    public ItemRating(int customer_id, int store_id, int item_id, int value) {
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.item_id = item_id;
        this.value = value;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getValue() {
        return value;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item_rating{" + "customer_id=" + customer_id + ", store_id=" + store_id + ", item_id=" + item_id + ", value=" + value + '}';
    }
    
    
}
