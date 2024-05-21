package entity;

public class Menu {
    private int menu_id;
    private int store_id;
    private String menu_image;

    public Menu() {
    }

    public Menu(int menu_id, int store_id, String menu_image) {
        this.menu_id = menu_id;
        this.store_id = store_id;
        this.menu_image = menu_image;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getMenu_image() {
        return menu_image;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }

    @Override
    public String toString() {
        return "Menu{" + "menu_id=" + menu_id + ", store_id=" + store_id + ", menu_image=" + menu_image + '}';
    }
    
    
}
