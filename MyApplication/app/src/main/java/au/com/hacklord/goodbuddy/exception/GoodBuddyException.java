package au.com.hacklord.goodbuddy.exception;

/**
 * Created by john on 27/08/2016.
 */
public class GoodBuddyException extends RuntimeException {
    public GoodBuddyException() {
        super();
    }

    public GoodBuddyException(String detailMessage) {
        super(detailMessage);
    }
}
