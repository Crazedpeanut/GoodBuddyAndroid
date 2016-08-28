package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.model.BuddyMessage;
import au.com.hacklord.goodbuddy.model.User;
import rx.Observable;

/**
 * Created by john on 28/08/2016.
 */
public interface IBuddyService {
    Observable<BuddyMessage> getBuddyMessages(User user);
    Observable<BuddyMessage> sendBuddyMessage(User user, BuddyMessage buddyMessage);
}
