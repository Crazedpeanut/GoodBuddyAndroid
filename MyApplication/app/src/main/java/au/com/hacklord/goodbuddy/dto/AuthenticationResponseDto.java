package au.com.hacklord.goodbuddy.dto;

import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.service.AbstractServiceResponse;

/**
 * Created by john on 27/08/2016.
 */
public class AuthenticationResponseDto extends AbstractServiceResponse<AuthenticationResponseDto> {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
