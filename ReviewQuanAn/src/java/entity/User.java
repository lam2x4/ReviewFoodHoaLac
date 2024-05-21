
package entity;

/**
 *
 * @author AnhTT
 */
public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private int gender;
    private String description;
    private int role_id;

    public User(int user_id, String user_name, String password, String email, String phone, String avatar, int gender, String description, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.role_id = role_id;
    }
    public User(String user_name, String password, String email, String phone, String avatar, int gender, String description, int role_id) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.role_id = role_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUse_rname() {
        return user_name;
    }

    public void setAccount(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" + "user_id: " + user_id + ", user_name: " + user_name + ", password: " + password + ", email: " + email + 
                ", phone: " + phone + ", avatar: " + avatar + ", gender: " + gender + ", description: " + description + 
                ", role_id: " + role_id + '}';
    }
}

