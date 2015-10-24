package fr.gobelins.crm14.workshop_android_crm14.discussion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.discussion.fragments.ChatFragment;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/24/15.
 */
public class DiscussionActivity extends AppCompatActivity implements ChatFragment.OnFragmentInteractionListener {
    private static final String TAG = "DiscussionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User contact = getIntent().getParcelableExtra("contact");
        Log.d(TAG, "Retrieved contact: " + contact.getUsername());


        setContentView(R.layout.activity_discussion);

        // Instantiate toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(contact.getUsername());

        // Load login fragment
        loadChatFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    private void loadChatFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.discussionContainer, new ChatFragment())
                .commit();

        //getSupportActionBar().setTitle(R.string.home_toolbar_login_title);
    }
}
