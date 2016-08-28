package au.com.hacklord.goodbuddy.viewmodel;

import android.databinding.ObservableField;

import rx.Observable;

/**
 * Created by john on 28/08/2016.
 */
public class BuddyAnswerViewModel {
    ObservableField<String> answer = new ObservableField<>();

    public ObservableField<String> getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }
}
