package au.com.hacklord.goodbuddy.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import au.com.hacklord.goodbuddy.BR;
import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.model.BuddyMessage;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.service.BuddyService;
import au.com.hacklord.goodbuddy.service.IBuddyService;
import au.com.hacklord.goodbuddy.session.UserSession;
import me.tatarka.bindingcollectionadapter.ItemView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by john on 28/08/2016.
 */
public class BuddyViewModel {

    static final String TAG = "BuddyViewModel";

    private ObservableField<String> comment = new ObservableField<>();
    private ObservableArrayList<BuddyAnswerViewModel> questionAnswers = new ObservableArrayList<>();
    private ObservableField<Boolean> showSpeech = new ObservableField<>();

    ItemView questionAnswerItem = ItemView.of(BR.answer, R.layout.buddy_question_answer);

    Subscription buddyMessageSubscription;
    User currentLoggedInUser;;

    public BuddyViewModel()
    {
        currentLoggedInUser = UserSession.getCurrentSessionUserState();
    }

    public void onFragmentAttach()
    {
        IBuddyService buddyService = new BuddyService();
        buddyService.getBuddyMessages(currentLoggedInUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BuddyMessage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error getting question msg" + e.getMessage());
                    }

                    @Override
                    public void onNext(BuddyMessage buddyMessage) {
                        Log.d(TAG, "Recieved buddy msg");

                        showSpeech.set(Boolean.TRUE);
                        questionAnswers.clear();

                        switch(buddyMessage.getMessageType())
                        {
                            case QUESTION:
                            {
                                Log.d(TAG, "Question rcvd");
                                comment.set(buddyMessage.getComment());

                                for (String answer: buddyMessage.getQuestions())
                                {
                                    BuddyAnswerViewModel answerVM = new BuddyAnswerViewModel();
                                    answerVM.setAnswer(answer);
                                    questionAnswers.add(answerVM);
                                    Log.d(TAG, answer);
                                }
                            }
                            break;

                            case COMMENT:
                            {
                                Log.d(TAG, "Comment rcvd");
                                comment.set(buddyMessage.getComment());
                            }
                            break;

                            default:
                            {
                                Log.d(TAG, "No buddy message type!");
                            }
                        }
                    }
                });

        showSpeech.set(Boolean.FALSE);
    }

    public void onFragmentDettach()
    {
        buddyMessageSubscription.unsubscribe();
    }

    public ObservableArrayList<BuddyAnswerViewModel> getQuestionAnswers() {
        return questionAnswers;
    }

    public ItemView getQuestionAnswerItem() {
        return questionAnswerItem;
    }

    public ObservableField<String> getComment() {
        return comment;
    }

    public ObservableField<Boolean> getShowSpeech() {
        return showSpeech;
    }

    public void setShowSpeech(ObservableField<Boolean> showSpeech) {
        this.showSpeech = showSpeech;
    }
}
