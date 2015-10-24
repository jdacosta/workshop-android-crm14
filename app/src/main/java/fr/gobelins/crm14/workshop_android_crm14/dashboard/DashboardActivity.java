package fr.gobelins.crm14.workshop_android_crm14.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.dashboard.adapter.SectionsPagerAdapter;
import fr.gobelins.crm14.workshop_android_crm14.discussion.DiscussionActivity;
import fr.gobelins.crm14.workshop_android_crm14.discussion.fragments.InboxFragment;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.UserService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;
import fr.gobelins.crm14.workshop_android_crm14.user.events.OpenDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ContactFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ProfileFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.dialog.AddContactDialogFragment;

public class DashboardActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener, InboxFragment.OnFragmentInteractionListener {

    private static final String TAG = "DashboardActivity";
    private AddContactDialogFragment addContactDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        // instantiate toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dashboardToolbar);
        setSupportActionBar(toolbar);

        // instantiate viewpager
        ViewPager viewPager = (ViewPager) findViewById(R.id.dashboardViewpager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        // instantiate tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.dashboardTabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuDashboardSettingsItem) {
            return true;
        }
        else if (item.getItemId() == R.id.menuDashboardLogoutItem) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
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

    private void showAddContactDialog() {
        FragmentManager fm = getSupportFragmentManager();
        addContactDialogFragment = new AddContactDialogFragment();
        addContactDialogFragment.show(fm, "fragment_add_contact");
    }

    @Override
    public void onAddContactFabClick() {
        showAddContactDialog();
    }

    @Subscribe
    public void onFindUserByUsername(FindUserByUsernameEvent event) {
        if (event.getRequestId() == FindUserByUsernameEvent.FIND_USER_TO_ADD_CONTACT &&
                !event.hasError() && event.hasFoundUser()) {
            addContactDialogFragment.dismiss();
        }
    }

    @Subscribe
    public void onOpenDiscussion(OpenDiscussionEvent event) {
        User contact = event.getContact();
        Intent discussionIntent = new Intent(this, DiscussionActivity.class);
        discussionIntent.putExtra("contact", contact);

        startActivity(discussionIntent);
    }
}
