package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.AppError;

/**
 * Created by john on 27/08/2016.
 */
public class ErrorEvent implements IRxEvent<AppError> {

    AppError error;

    @Override
    public AppError getData() {
        return this.error;
    }

    @Override
    public void setData(AppError error) {
        this.error = error;
    }
}
