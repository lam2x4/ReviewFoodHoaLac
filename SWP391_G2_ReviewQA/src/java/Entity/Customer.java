/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author TRAN TUAN ANH
 */
public class Customer {
    private int id;
    private String name;
    private String account;
    private String password;
    private int isBanned;

    public Customer(int id, String name, String account, String password, int isBanned) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.isBanned = isBanned;
    }
    
    public Customer(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + ", isBanned=" + isBanned + '}';
    }
    
}
