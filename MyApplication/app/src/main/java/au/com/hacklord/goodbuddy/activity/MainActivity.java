package au.com.hacklord.goodbuddy.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.fragment.LoginFragment;
import au.com.hacklord.goodbuddy.manager.UserManager;
import au.com.hacklord.goodbuddy.model.AppError;
import au.com.hacklord.goodbuddy.rx.IRxEvent;
import au.com.hacklord.goodbuddy.session.SessionErrors;
import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginSuccessListener {

    LoginFragment loginFragment;

    View rootView;
    View fragmentContainer;
    Subscription errorSubscription;

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentContainer = findViewById(R.id.main_fragment_container);
        rootView = getWindow().getDecorView().getRootView();
        initFragments();
    }


    @Override
    protected void onResume() {
        super.onResume();

        errorSubscription = SessionErrors.getInstance().getEventBus().getEvents()
                .subscribe(new Subscriber<IRxEvent<AppError>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IRxEvent<AppError> appErrorIRxEvent) {
                        displaySnackbar(appErrorIRxEvent.getData().getMessage());
                    }
                });

        checkIsLoggedIn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        errorSubscription.unsubscribe();
    }

    void displaySnackbar(String message)
    {
        if(snackbar == null)
        {
            snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        }
        else if(snackbar.isShown())
        {
            snackbar.dismiss();
        }
    }

    void checkIsLoggedIn()
    {
        if(!UserManager.isLoggedIn())
        {
            swapFragment(loginFragment, false);
        }
    }

    void initFragments()
    {
        loginFragment = LoginFragment.newInstance();
    }

    void swapFragment(Fragment fragment, boolean addToBackStack)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment_container, fragment);

        if(addToBackStack)
        {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onLoginSuccess() {
    }
}
