package bean;

public class User {
    private String username;
    private String password;
    private String phonenumber;
    private String email;

    public User(String username, String password, String phonenumber, String email) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public User() {
    }

    public String getUsername() {

        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
