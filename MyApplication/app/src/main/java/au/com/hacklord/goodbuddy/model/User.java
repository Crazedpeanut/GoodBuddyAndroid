package au.com.hacklord.goodbuddy.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by john on 27/08/2016.
 */
public class User {

    @SerializedName("_id")
    private String id;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("username")
    private String userName;
    private String password;
    private String email;
    private boolean isLoggedIn;
    private String fcmId;

    public User clone() {
        User userClone = new User();

        userClone.setEmail(email);
        userClone.setId(id);
        userClone.setLastName(lastName);
        userClone.setFirstName(firstName);
        userClone.setUsername(userName);
        userClone.setLoggedIn(isLoggedIn);
        userClone.setFcmId(fcmId);

        return userClone;
    }

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
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
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
