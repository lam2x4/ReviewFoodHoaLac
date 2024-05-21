package entity;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private int gender;
    private String description;
    private int role_id;

    public User() {
    }

    public User(int user_id, String username, String password, String email, String phone, String avatar, int gender, String description, int role_id) {
        this.user_id = user_id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }
    
    public int getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", avatar=" + avatar + ", gender=" + gender + ", description=" + description + ", role_id=" + role_id + '}';
    }

    
}
