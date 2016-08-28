package au.com.hacklord.goodbuddy.dto;

import au.com.hacklord.goodbuddy.model.User;

/**
 * Created by john on 28/08/2016.
 */
public class UpdateUserResponseDto {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
