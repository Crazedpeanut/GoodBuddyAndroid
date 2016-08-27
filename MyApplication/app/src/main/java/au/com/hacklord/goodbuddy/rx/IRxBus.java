package au.com.hacklord.goodbuddy.rx;

import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public interface IRxBus<EventData> {
    public Observable<IRxEvent<EventData>> getEvents();
    public void putEvent(IRxEvent<EventData> event);
    public void putEvent(EventData eventData);
}

