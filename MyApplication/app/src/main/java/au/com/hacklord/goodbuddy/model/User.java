package au.com.hacklord.goodbuddy.model;

/**
 * Created by john on 27/08/2016.
 */
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private boolean isLoggedIn;

    public User clone() {
        User userClone = new User();

        userClone.setEmail(email);
        userClone.setId(id);
        userClone.setLastName(lastName);
        userClone.setFirstName(firstName);
        userClone.setUsername(username);
        userClone.setLoggedIn(isLoggedIn);

        return userClone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
