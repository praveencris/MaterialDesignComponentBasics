package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.codelabs.mdc.java.shrine.databinding.ShrLoginFragmentBinding;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    private ShrLoginFragmentBinding mBinding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.shr_login_fragment, container, false);
        View view = mBinding.getRoot();
        // Snippet from "Navigate to the next Fragment" section goes here.


        // Set an error if the password is less than 8 characters.
        mBinding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(mBinding.passwordEditText.getText())) {
                    mBinding.passwordTextInput.setError(getString(R.string.shr_error_password));
                } else {
                    mBinding.passwordTextInput.setError(null);// Clear the error

                    //Navigate to another fragment
                    ((NavigationHost)getActivity()).navigateTo(new ProductGridFragment(),false);
                }
            }
        });
        // Clear the error once more than 8 characters are typed.
        mBinding.passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(mBinding.passwordEditText.getText())) {
                    mBinding.passwordEditText.setError(null); //Clear the error
                }
                return false;            }
        });

        return view;
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here

    /*
   In reality, this will have more complex logic including, but not limited to, actual
   authentication of the username and password.
*/
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}
