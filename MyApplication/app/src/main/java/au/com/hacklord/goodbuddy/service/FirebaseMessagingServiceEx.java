package au.com.hacklord.goodbuddy.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import au.com.hacklord.goodbuddy.model.FirebaseMessage;
import au.com.hacklord.goodbuddy.session.FirebaseMessageSession;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessagingServiceEx extends FirebaseMessagingService{

    static final String TAG = "FirebaseMessagingServi";

    FirebaseMessageSession messageSession = FirebaseMessageSession.getInstance();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Recieved firebase message");
        FirebaseMessage firebaseMessage = new FirebaseMessage();

        firebaseMessage.setData(remoteMessage.getData());
        firebaseMessage.setId(remoteMessage.getMessageId());
        firebaseMessage.setTo(remoteMessage.getTo());
        firebaseMessage.setFrom(remoteMessage.getFrom());
        firebaseMessage.setDirection(FirebaseMessage.Direction.DOWN_STREAM);

        messageSession.getEventBus().putEvent(firebaseMessage);
    }
}
