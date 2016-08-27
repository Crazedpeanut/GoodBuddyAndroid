package au.com.hacklord.goodbuddy.model;

import au.com.hacklord.goodbuddy.service.AbstractServiceResponse;

/**
 * Created by john on 27/08/2016.
 */
public class AuthenticationResponse extends AbstractServiceResponse<AuthenticationResponse> {
    private String error;
    private User user;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
