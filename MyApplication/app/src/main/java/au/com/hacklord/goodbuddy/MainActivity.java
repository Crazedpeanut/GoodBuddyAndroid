package au.com.hacklord.goodbuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import au.com.hacklord.goodbuddy.fragment.LoginFragment;
import au.com.hacklord.goodbuddy.manager.UserManager;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;

    View fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentContainer = findViewById(R.id.main_fragment_container);

        initFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkIsLoggedIn();
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

}
