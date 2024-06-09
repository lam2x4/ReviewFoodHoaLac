package entity;

/**
 *
 * @author AnhTT
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private int gender;
    private String description;
    private String create_date;
    private int verify_status;
    private int role_id;

    public User() {
    }

    public User(int id, String username, String password, String email, String phone, String avatar, int gender, String description, String create_date, int verify_status, int role_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.create_date = create_date;
        this.verify_status = verify_status;
        this.role_id = role_id;
    }

    public User(String username, String password, String email, String phone, String avatar, int gender, String description, String create_date, int verify_status, int role_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.create_date = create_date;
        this.verify_status = verify_status;
        this.role_id = role_id;
    }

    public int getId() {
        return id;
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

    public String getCreate_date() {
        return create_date;
    }

    public int getVerify_status() {
        return verify_status;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setVerify_status(int verify_status) {
        this.verify_status = verify_status;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", avatar=" + avatar + ", gender=" + gender + ", description=" + description + ", create_date=" + create_date + ", verify_status=" + verify_status + ", role_id=" + role_id + '}';
    }

}
