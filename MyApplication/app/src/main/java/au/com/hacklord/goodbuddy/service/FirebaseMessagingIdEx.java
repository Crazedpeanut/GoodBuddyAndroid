package au.com.hacklord.goodbuddy.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessagingIdEx extends FirebaseInstanceIdService {

    static final String TAG = "FirebaseMessagingIdEx";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

}
