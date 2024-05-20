package entity;

public class StoreManager {
    private int id;
    private String name;
    private String account;
    private String password;
    private boolean is_banned;
    private int store_id;

    public StoreManager() {
    }

    public StoreManager(int id, String name, String account, String password, int store_id) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        is_banned = false;
        this.store_id = store_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "StoreManager{" + "id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + ", is_banned=" + is_banned + ", store_id=" + store_id + '}';
    }
    
    
}
