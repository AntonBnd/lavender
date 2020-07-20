package by.lavender.beans;

public class User extends Entity {
    private long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String login;
    private Role role;
    private String phoneNumber;

    public User() {
    }

    public User(String firstName, String lastName, String password, String login, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.phoneNumber = phoneNumber;
    }

    public User(long userId, String firstName, String lastName, String password, String login, Role role, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}