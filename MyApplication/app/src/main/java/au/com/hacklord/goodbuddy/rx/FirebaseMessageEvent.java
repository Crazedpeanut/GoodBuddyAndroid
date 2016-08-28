package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.FirebaseMessage;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessageEvent implements IRxEvent<FirebaseMessage> {

    private FirebaseMessage firebaseMessage;

    @Override
    public FirebaseMessage getData() {
        return firebaseMessage;
    }

    @Override
    public void setData(FirebaseMessage firebaseMessage) {
        this.firebaseMessage = firebaseMessage;
    }
}
