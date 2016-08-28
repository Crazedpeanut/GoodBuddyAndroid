package au.com.hacklord.goodbuddy.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import au.com.hacklord.goodbuddy.R;
import au.com.hacklord.goodbuddy.activity.MainActivity;
import au.com.hacklord.goodbuddy.databinding.FragmentBuddyBinding;
import au.com.hacklord.goodbuddy.viewmodel.BuddyViewModel;

public class BuddyFragment extends Fragment {

    View fragmentRootView;
    FragmentBuddyBinding binding;
    BuddyViewModel buddyViewModel;

    public BuddyFragment() {
        // Required empty public constructor
    }

    public static BuddyFragment newInstance() {
        BuddyFragment fragment = new BuddyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buddy, container, false);
        binding.setBuddyViewModel(buddyViewModel);

        fragmentRootView = binding.getRoot();
        return fragmentRootView;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        buddyViewModel = new BuddyViewModel();
        buddyViewModel.onFragmentAttach();

    }

    @Override
    public void onDetach() {
        buddyViewModel.onFragmentDettach();
        super.onDetach();
    }

}
