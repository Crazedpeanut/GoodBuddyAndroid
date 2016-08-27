package au.com.hacklord.goodbuddy.rx;

/**
 * Created by john on 27/08/2016.
 */
public interface IRxEvent<EventData> {
    public EventData getData();
    public void setData(EventData data);
}
