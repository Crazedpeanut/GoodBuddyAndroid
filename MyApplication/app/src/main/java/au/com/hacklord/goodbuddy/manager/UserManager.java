package au.com.hacklord.goodbuddy.manager;


import android.util.Log;

import au.com.hacklord.goodbuddy.dto.AuthenticationResponseDto;
import au.com.hacklord.goodbuddy.exception.GoodBuddyAuthenticationException;
import au.com.hacklord.goodbuddy.model.AppError;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.service.AuthServiceHelper;
import au.com.hacklord.goodbuddy.session.SessionErrors;
import au.com.hacklord.goodbuddy.session.UserSession;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by john on 27/08/2016.
 */
public class UserManager {

    static final String TAG = "UserManager";

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

    public void attemptLogin()
    {
        AuthServiceHelper authServiceHelper = new AuthServiceHelper();

        Log.d(TAG, "Attempting log in");

        authServiceHelper.attemptAuth(user.getUsername(), user.getPassword())
                .map(new Func1<AuthenticationResponseDto, User>()
                {
                    @Override
                    public User call(AuthenticationResponseDto dto) {
                        return validateResponse(dto);
                    }
                })
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed attempt auth request");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        SessionErrors.createError(e.getMessage(), AppError.ErrorType.AUTH);
                    }

                    @Override
                    public void onNext(User user) {
                        updateUserSession(user);
                    }
                });
    }

    void updateUserSession(User user)
    {
        Log.d(TAG, "Updating user session");
        UserSession.getInstance().getEventBus().putEvent(user);
    }

    User validateResponse(AuthenticationResponseDto dto) throws GoodBuddyAuthenticationException
    {
        User user;

        if(dto.getError().length() != 0)
        {
            throw new GoodBuddyAuthenticationException(dto.getError());
        }

        user = getUser();
        user.setLoggedIn(true);

        return user;
    }



}
