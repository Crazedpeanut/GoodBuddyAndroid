package au.com.hacklord.goodbuddy.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.databinding.FragmentLoginBinding;
import au.com.hacklord.goodbuddy.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

 /*   public interface OnLoginSuccessListener
    {
        void onLoginSuccess();
    }

    OnLoginSuccessListener onLoginSuccessListener;*/

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

        FragmentLoginBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_login);
        binding.setLoginViewModel(new LoginViewModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
/*        if (context instanceof OnLoginSuccessListener) {
            onLoginSuccessListener = (OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       /* onLoginSuccessListener = null;*/
    }
}
