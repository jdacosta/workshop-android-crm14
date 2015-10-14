package fr.gobelins.crm14.workshop_android_crm14.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.home.HomeActivity;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.message.fragments.InboxFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.User;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ContactFragment;
import fr.gobelins.crm14.workshop_android_crm14.user.fragments.ProfileFragment;

public class DashboardActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener, InboxFragment.OnFragmentInteractionListener {

    private static final String TAG = "DashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User currentUser = AuthService.getInstance().getCurrentUser();

        if (currentUser == null) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        } else {
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
}
