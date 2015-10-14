package fr.gobelins.crm14.workshop_android_crm14.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment implements AuthService.RegisterHandler {

    private static final String TAG = "RegisterFragment";
    private OnFragmentInteractionListener mListener;

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
        mListener = null;
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.homeRegisterButton)
    public void onRegisterButtonClick() {
        AuthService.getInstance()
                .register(
                        emailField.getText().toString(),
                        usernameField.getText().toString(),
                        passwordField.getText().toString(),
                        this);
    }

    @Override
    public void onRegisterSuccess() {
        mListener.onRegister();
    }

    @Override
    public void onRegisterFail(String error) {
        // TODO: Create snack bar message
    }

    public interface OnFragmentInteractionListener {
        void onRegister();
    }

}
