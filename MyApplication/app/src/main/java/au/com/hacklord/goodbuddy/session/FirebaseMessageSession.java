package au.com.hacklord.goodbuddy.session;

import au.com.hacklord.goodbuddy.rx.FirebaseMessageBus;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessageSession {
    static final String TAG = "FirebaseMessageSession";

    private static FirebaseMessageSession instance;
    private FirebaseMessageBus eventBus;

    protected FirebaseMessageSession()
    {
        eventBus = new FirebaseMessageBus();
    }

    public static FirebaseMessageSession getInstance()
    {
        if(instance == null)
        {
            instance = new FirebaseMessageSession();
        }

        return instance;
    }

    public FirebaseMessageBus getEventBus()
    {
        return eventBus;
    }
}
