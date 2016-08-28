package au.com.hacklord.goodbuddy.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.activity.MainActivity;
import au.com.hacklord.goodbuddy.databinding.FragmentLoginBinding;
import au.com.hacklord.goodbuddy.manager.UserManager;
import au.com.hacklord.goodbuddy.model.User;
import au.com.hacklord.goodbuddy.rx.IRxEvent;
import au.com.hacklord.goodbuddy.session.UserSession;
import au.com.hacklord.goodbuddy.viewmodel.LoginViewModel;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class LoginFragment extends Fragment {

    public interface LoginSuccessListener
    {
        public void onLoginSuccess();
    }

    LoginSuccessListener loginSuccessListener;

    View fragmentRootView;
    LoginViewModel loginViewModel;
    Button signUpButton;
    RegistrationFragment registrationFragment;

    FragmentLoginBinding binding;

    static final String TAG = "LoginFragment";

    Subscription userSessionSubscription;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registrationFragment = RegistrationFragment.newInstance();
        loginViewModel = new LoginViewModel(new UserManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLoginViewModel(loginViewModel);

        fragmentRootView = binding.getRoot();

        signUpButton = (Button)fragmentRootView.findViewById(R.id.signupbutton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.swapViewRequestListener.onSwapViewRequest(registrationFragment, true);
            }
        });

        return fragmentRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LoginSuccessListener)
        {
            loginSuccessListener = (LoginSuccessListener) context;
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
    public void onDetach() {
        super.onDetach();

       loginSuccessListener = null;
        userSessionSubscription.unsubscribe();
    }
}
