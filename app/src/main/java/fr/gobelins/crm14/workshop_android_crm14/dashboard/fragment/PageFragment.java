package fr.gobelins.crm14.workshop_android_crm14.dashboard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import fr.gobelins.crm14.workshop_android_crm14.message.fragments.InboxFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ContactFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ProfileFragment;

public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";


    public static Fragment newInstance(int page) {
        Fragment fragment;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        // dashboard fragment
        if (page == 1) {
            fragment = new InboxFragment();
        }

        // inbox fragment
        else if (page == 2) {
            fragment = new ContactFragment();
        }

        // contact fragment
        else {
            fragment = new ProfileFragment();
        }

        fragment.setArguments(args);
        return fragment;
    }
}
