package fr.gobelins.crm14.workshop_android_crm14.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication.AuthenticationEvent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.homeLoginEmailField) TextView emailField;
    @Bind(R.id.homeLoginPasswordField) TextView passwordField;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

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
    public void onDetach() {
        super.onDetach();
        BusProvider.getInstance().unregister(this);
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.homeLoginButton)
    public void onLoginButtonClick() {
        mListener.onLoginStart();
        AuthService.getInstance()
                .authenticate(
                        emailField.getText().toString(),
                        passwordField.getText().toString());
    }

    @Subscribe
    public void onUserAuthenticate(AuthenticationEvent event) {
        if (event.hasError()) {
            Snackbar.make(getView(), event.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public interface OnFragmentInteractionListener {
        void onLoginStart();
    }
}
