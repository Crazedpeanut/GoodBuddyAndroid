package au.com.hacklord.goodbuddy.rx;

import au.com.hacklord.goodbuddy.model.AppError;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by john on 27/08/2016.
 */
public class ErrorEventBus implements IRxBus<AppError>{

    PublishSubject<IRxEvent<AppError>> errors = PublishSubject.create();

    @Override
    public Observable<IRxEvent<AppError>> getEvents() {
        return errors.asObservable();
    }

    @Override
    public void putEvent(IRxEvent<AppError> event) {
        errors.onNext(event);
    }

    @Override
    public void putEvent(AppError error) {
        ErrorEvent errorEvent = new ErrorEvent();
        errorEvent.setData(error);
        putEvent(errorEvent);
    }
}
