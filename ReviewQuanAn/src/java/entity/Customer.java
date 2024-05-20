package entity;

public class Customer {
    private int id;
    private String name;
    private String account;
    private String password;
    private boolean is_banned;

    public Customer() {
    }

    public Customer(int id, String name, String account, String password) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        is_banned = false;
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

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + ", is_banned=" + is_banned + '}';
    }
    
    
}
