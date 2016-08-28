package au.com.hacklord.goodbuddy.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.databinding.FragmentRegistBinding;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.rx.IRxEvent;
import au.com.hacklord.goodbuddy.session.UserSession;
import au.com.hacklord.goodbuddy.viewmodel.RegistViewModel;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class RegistrationFragment extends Fragment {

    static final String TAG = "RegistrationFragment";

    FragmentRegistBinding binding;
    RegistViewModel registViewModel;
    View fragmentRootView;
    Subscription userSessionSubscription;

    LoginFragment.LoginSuccessListener loginSuccessListener;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registViewModel = new RegistViewModel();

        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LoginFragment.LoginSuccessListener)
        {
            loginSuccessListener = (LoginFragment.LoginSuccessListener) context;
        }
        else
        {
            throw new RuntimeException("Must implement LoginSuccessListener");
        }

        userSessionSubscription = UserSession.getInstance().getEventBus().getEvents()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IRxEvent<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error!" + e);
                    }

                    @Override
                    public void onNext(IRxEvent<User> userIRxEvent) {

                        Log.d(TAG, "User event recieved!");

                        if(userIRxEvent.getData().getIsLoggedIn())
                        {
                            Log.d(TAG, "Is logged in");
                            loginSuccessListener.onLoginSuccess();
                        }
                    }
                });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_regist, container, false);
        binding.setRegistViewModel(registViewModel);

        fragmentRootView = binding.getRoot();

        return fragmentRootView;
    }

}
