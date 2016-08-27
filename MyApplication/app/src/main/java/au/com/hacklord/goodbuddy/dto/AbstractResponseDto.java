package au.com.hacklord.goodbuddy.dto;

/**
 * Created by john on 27/08/2016.
 */
public abstract class AbstractResponseDto {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
