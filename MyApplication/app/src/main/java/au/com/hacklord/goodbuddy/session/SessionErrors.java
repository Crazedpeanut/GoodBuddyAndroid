package au.com.hacklord.goodbuddy.session;

import au.com.hacklord.goodbuddy.model.AppError;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.rx.ErrorEvent;
import au.com.hacklord.goodbuddy.rx.ErrorEventBus;
import au.com.hacklord.goodbuddy.rx.UserEventBus;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public class SessionErrors {
    static final String TAG = "SessionErrors";

    private static SessionErrors instance;
    private ErrorEventBus eventBus;

    protected SessionErrors()
    {
        eventBus = new ErrorEventBus();
    }

    public static SessionErrors getInstance()
    {
        if(instance == null)
        {
            instance = new SessionErrors();
        }

        return instance;
    }

    public ErrorEventBus getEventBus()
    {
        return eventBus;
    }

    public static void createError(String message, AppError.ErrorType errorType)
    {
        AppError error = new AppError();
        error.setMessage(message);
        error.setErrorType(errorType);

        instance.eventBus.putEvent(error);
    }

}
