package au.com.hacklord.goodbuddy.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import au.com.hacklord.goodbuddy.model.BuddyMessage;
import au.com.hacklord.goodbuddy.model.FirebaseMessage;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.rx.IRxEvent;
import au.com.hacklord.goodbuddy.session.FirebaseMessageSession;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by john on 28/08/2016.
 */
public class BuddyService implements IBuddyService {

    static final String TAG = "BuddyService";

    static final String VALUE_BUDDY_MESSAGE = "BUDDY_MESSAGE";
    static final String KEY_CATEGORY = "CATEGORY";
    static final String KEY_BUDDY_MESSAGE_TYPE = "MESSAGE_TYPE";
    static final String VALUE_BUDDY_MESSAGE_TYPE_QUESTION = "QUESTION";
    static final String VALUE_BUDDY_MESSAGE_TYPE_COMMENT = "COMMENT";
    static final String KEY_COMMENT = "COMMENT";
    static final String KEY_QUESTION = "QUESTION";

    FirebaseMessageSession messageSession = FirebaseMessageSession.getInstance();

    @Override
    public Observable<BuddyMessage> getBuddyMessages(User user) {
        return messageSession.getEventBus().getEvents()
                .filter(new Func1<IRxEvent<FirebaseMessage>, Boolean>() {
                    @Override
                    public Boolean call(IRxEvent<FirebaseMessage> firebaseMessageIRxEvent) {
                        FirebaseMessage firebaseMessage = firebaseMessageIRxEvent.getData();

                         if(firebaseMessage.getDirection() == FirebaseMessage.Direction.DOWN_STREAM)
                         {
                             Log.d(TAG, "Message is downstream");
                             return true;
                         }
                        else
                         {
                             Log.d(TAG, "Message is upstream, filtering out");
                             return false;
                         }
                    }
                })
                .filter(new Func1<IRxEvent<FirebaseMessage>, Boolean>() {
                    @Override
                    public Boolean call(IRxEvent<FirebaseMessage> firebaseMessageIRxEvent) {
                        FirebaseMessage firebaseMessage = firebaseMessageIRxEvent.getData();

                        if(firebaseMessage.getData().containsKey(KEY_CATEGORY))
                        {
                            if(firebaseMessage.getData().get(KEY_CATEGORY).equals(VALUE_BUDDY_MESSAGE))
                            {
                                Log.d(TAG, "CATEGORY IS " + VALUE_BUDDY_MESSAGE);
                                return true;
                            }
                            else
                            {
                                Log.d(TAG, "CATEGORY ISNT " + VALUE_BUDDY_MESSAGE +" filtering out");
                                return false;
                            }
                        }

                        Log.d(TAG, "Doesn't contain key " + KEY_CATEGORY + " filtering out");

                        return false;
                    }
                })
                .map(new Func1<IRxEvent<FirebaseMessage>, BuddyMessage>() {
                    @Override
                    public BuddyMessage call(IRxEvent<FirebaseMessage> firebaseMessageIRxEvent) {
                        Log.d(TAG, "Mapping from IRxEvent<FirebaseMessage> to buddy message");
                        BuddyMessage message = getBuddyMessage(firebaseMessageIRxEvent.getData());
                        Log.d(TAG, "completed Mapping from IRxEvent<FirebaseMessage> to buddy message");
                        return message;
                    }
                });
    }

    private BuddyMessage getBuddyMessage(FirebaseMessage firebaseMessage)
    {
        BuddyMessage buddyMessage = new BuddyMessage();

        buddyMessage.setMessageId(firebaseMessage.getId());

        if(firebaseMessage.getData().containsKey(KEY_BUDDY_MESSAGE_TYPE))
        {
            switch(firebaseMessage.getData().get(KEY_BUDDY_MESSAGE_TYPE))
            {
                case VALUE_BUDDY_MESSAGE_TYPE_COMMENT:
                {
                    buddyMessage.setMessageType(BuddyMessage.BuddyMessageType.COMMENT);
                    buddyMessage.setComment(firebaseMessage.getData().get(KEY_COMMENT));
                }
                break;

                case VALUE_BUDDY_MESSAGE_TYPE_QUESTION:
                {
                    buddyMessage.setMessageType(BuddyMessage.BuddyMessageType.QUESTION);
                    buddyMessage.setQuestions(getQuestionAnswers(firebaseMessage));
                }
                break;

                default:
                {
                    Log.e(TAG, "Buddy message doesn't have a message type!");
                }
            }
        }

        buddyMessage.setComment(firebaseMessage.getData().get(KEY_COMMENT));

        return buddyMessage;
    }

    List<String> getQuestionAnswers(FirebaseMessage firebaseMessage)
    {
        int currentQuestionKey = 0;
        List<String> questionAnswers = new ArrayList<>();

        while(firebaseMessage.getData().containsKey(KEY_QUESTION+currentQuestionKey))
        {
            questionAnswers.add(firebaseMessage.getData().get(KEY_QUESTION+currentQuestionKey));
            currentQuestionKey++;
        }

        return questionAnswers;
    }

    @Override
    public Observable<BuddyMessage> sendBuddyMessage(User user, BuddyMessage buddyMessage) {
        return null;
    }
}
