package au.com.hacklord.goodbuddy.exception;

/**
 * Created by john on 27/08/2016.
 */
public class GoodBuddyAuthenticationException extends GoodBuddyException {
    public GoodBuddyAuthenticationException() {
        super();
    }

    public GoodBuddyAuthenticationException(String detailMessage) {
        super(detailMessage);
    }
}
