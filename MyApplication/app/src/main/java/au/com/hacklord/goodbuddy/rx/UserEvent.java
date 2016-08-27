package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.User;

/**
 * Created by john on 27/08/2016.
 */
public class UserEvent implements IRxEvent<User> {

    User user;

    @Override
    public User getData() {
        return user;
    }

    @Override
    public void setData(User user) {
        this.user = user;
    }
}

