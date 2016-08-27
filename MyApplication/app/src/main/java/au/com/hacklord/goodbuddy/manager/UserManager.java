package au.com.hacklord.goodbuddy.manager;

import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.session.UserSession;

/**
 * Created by john on 27/08/2016.
 */
public class UserManager {
    User user;

    public UserManager()
    {

    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return this.user;
    }

    public static boolean isLoggedIn()
    {
        return UserSession.isLoggedIn();
    }

}
