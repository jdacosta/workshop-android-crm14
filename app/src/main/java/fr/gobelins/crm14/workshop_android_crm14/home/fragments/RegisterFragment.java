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
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthenticationEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.RegisterEvent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    @Bind(R.id.homeRegisterEmailField) TextView emailField;
    @Bind(R.id.homeRegisterUsernameField) TextView usernameField;
    @Bind(R.id.homeRegisterPasswordField) TextView passwordField;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
    }

    @OnClick(R.id.homeRegisterButton)
    public void onRegisterButtonClick() {
        AuthService.getInstance()
                .register(
                        emailField.getText().toString(),
                        usernameField.getText().toString(),
                        passwordField.getText().toString());
    }

    @Subscribe
    public void onUserRegister(RegisterEvent event) {
        if (event.hasError()) {
            Snackbar.make(getView(), event.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}
