package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.User;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by john on 27/08/2016.
 */
public class UserEventBus implements IRxBus<User> {

    PublishSubject<IRxEvent<User>> subject = PublishSubject.create();

    @Override
    public Observable<IRxEvent<User>> getEvents() {
        return subject.asObservable();
    }

    @Override
    public void putEvent(IRxEvent<User> event) {
        subject.onNext(event);
    }

    @Override
    public void putEvent(User user) {
        UserEvent event = new UserEvent();
        event.setData(user);

        subject.onNext(event);
    }
}
