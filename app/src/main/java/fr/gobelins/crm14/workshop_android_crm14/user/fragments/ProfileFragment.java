package fr.gobelins.crm14.workshop_android_crm14.user.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.updateEmail.UpdateEmailEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.userProfileUsernameField) TextView usernameField;
    @Bind(R.id.userProfileEmailField) EditText emailField;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.getInstance().unregister(this);
        ButterKnife.unbind(this);
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        ButterKnife.unbind(this);
        mListener = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BusProvider.getInstance().unregister(this);
        ButterKnife.unbind(this);
        mListener = null;
    }

    @OnClick(R.id.userProfileUpdateButton)
    public void onLoginButtonClick() {
        AuthService.getInstance().updateEmail(emailField.getText().toString());
    }

    private void updateUserData(User user) {
        usernameField.setText(user.getUsername());
    }

    private void displayCurrentEmail() {
        emailField.setText(AuthService.getInstance().getCurrentEmail());
    }

    @Subscribe
    public void onGetUserData(GetCurrentUserDataEvent event) {
        Log.d(TAG, "onGetUserData");
        if (!event.hasError()) {
            updateUserData(event.getUser());
            displayCurrentEmail();
            Log.d(TAG, "onGetUserData " + event.getUser().getUsername());
        }
    }

    @Subscribe
    public void onUpdateEmail(UpdateEmailEvent event) {
        if (event.hasError()) {
            Snackbar.make(getView(), event.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        }
        else {
            Snackbar.make(getView(), R.string.user_profile_email_snackbar_success, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public interface OnFragmentInteractionListener {
    }

}
