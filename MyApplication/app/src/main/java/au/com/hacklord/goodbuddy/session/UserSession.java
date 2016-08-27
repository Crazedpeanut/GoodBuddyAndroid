package au.com.hacklord.goodbuddy.session;

import android.util.Log;

import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.rx.IRxEvent;
import au.com.hacklord.goodbuddy.rx.UserEventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by john on 27/08/2016.
 */
public class UserSession {

    static final String TAG = "UserSession";

    User user;
    private static UserSession instance;
    private UserEventBus eventBus;

    protected UserSession()
    {
        user = null;
        eventBus = new UserEventBus();

        eventBus.getEvents()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IRxEvent<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "AppError subscribing to user event bus");
                    }

                    @Override
                    public void onNext(IRxEvent<User> userIRxEvent)
                    {
                        user = userIRxEvent.getData();
                    }
                });
    }

    public static UserSession getInstance()
    {
        if(instance == null)
        {
            instance = new UserSession();
        }

        return instance;
    }

    public static User getCurrentSessionUserState()
    {
        return UserSession.instance.user.clone();
    }

    public static boolean isLoggedIn()
    {
        if(getInstance().user == null || !getInstance().user.getIsLoggedIn())
        {
            return false;
        }

        return true;
    }

    public UserEventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(UserEventBus eventBus) {
        this.eventBus = eventBus;
    }
}
