package au.com.hacklord.goodbuddy.model;

import java.util.Map;

/**
 * Created by john on 28/08/2016.
 */
public class FirebaseMessage {

    public enum Direction{
        DOWN_STREAM,
        UP_STREAM
    }

    private String id;
    private Direction direction;
    private String to;
    private String from;
    private Map<String, String> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
