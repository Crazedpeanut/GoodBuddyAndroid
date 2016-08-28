package au.com.hacklord.goodbuddy.service;

/**
 * Created by john on 27/08/2016.
 */
public abstract class AbstractServiceResponse<ResponseType> {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
