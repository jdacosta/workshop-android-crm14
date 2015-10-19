package fr.gobelins.crm14.workshop_android_crm14.user.fragments.dialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.UserService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameEvent;

/**
 * Created by risq on 10/15/15.
 */
public class AddContactDialogFragment extends DialogFragment {

    @Bind(R.id.userContactAddUsernameField) TextView usernameField;
    @Bind(R.id.userContactAddErrorField) TextView errorField;

    public AddContactDialogFragment() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusProvider.getInstance().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_contact, null);

        builder
                .setView(view)
                .setTitle("Add contact");

        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);


        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameField.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @OnClick(R.id.userContactAddButton)
    public void onButtonClick() {
        errorField.setVisibility(View.INVISIBLE);
        UserService.getInstance()
                .addContactByUsername(usernameField.getText().toString());
    }


    @Subscribe
    public void onFindUserByUsername(FindUserByUsernameEvent event) {
        if (event.getRequestId() == FindUserByUsernameEvent.FIND_USER_TO_ADD_CONTACT &&
                !event.hasError() && !event.hasFoundUser()) {
            errorField.setVisibility(View.VISIBLE);
        }
    }
}
