package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.AppError;
import au.com.hacklord.goodbuddy.model.FirebaseMessage;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessageBus implements IRxBus<FirebaseMessage> {

    PublishSubject<IRxEvent<FirebaseMessage>> firebaseMessages = PublishSubject.create();

    @Override
    public Observable<IRxEvent<FirebaseMessage>> getEvents() {
        return firebaseMessages.asObservable();
    }

    @Override
    public void putEvent(IRxEvent<FirebaseMessage> event) {
        firebaseMessages.onNext(event);
    }

    @Override
    public void putEvent(FirebaseMessage firebaseMessage) {
        FirebaseMessageEvent messageEvent = new FirebaseMessageEvent();
        messageEvent.setData(firebaseMessage);

        putEvent(messageEvent);
    }
}
