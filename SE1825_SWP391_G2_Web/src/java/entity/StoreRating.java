package entity;

public class StoreRating {
    private int customer_id;
    private int store_id;
    private int value; //float?

    public StoreRating() {
    }

    public StoreRating(int customer_id, int store_id, int value) {
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.value = value;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getStore_id() {
        return store_id;
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

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StoreRating{" + "customer_id=" + customer_id + ", store_id=" + store_id + ", value=" + value + '}';
    }
    
    
}
