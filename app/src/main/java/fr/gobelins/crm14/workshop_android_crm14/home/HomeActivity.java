package fr.gobelins.crm14.workshop_android_crm14.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.dashboard.DashboardActivity;
import fr.gobelins.crm14.workshop_android_crm14.home.fragments.LoginFragment;
import fr.gobelins.crm14.workshop_android_crm14.home.fragments.RegisterFragment;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthenticationEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.RegisterEvent;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // instantiate toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);

        // instantiate login fragment
        loadLoginFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHomeLoginItem) {
            loadLoginFragment();
            return true;
        }
        else if (item.getItemId() == R.id.menuHomeRegisterItem) {
            loadRegisterFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    private void loadLoginFragment() {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.homeContainer, new LoginFragment())
            .commit();

        getSupportActionBar().setTitle(R.string.home_toolbar_login_title);
    }

    private void loadRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.homeContainer, new RegisterFragment())
                .commit();

        getSupportActionBar().setTitle(R.string.home_toolbar_register_title);
    }

    @Subscribe
    public void onUserAuthenticate(AuthenticationEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "onLogin SUCCESS !!!");
            Intent dashboardIntent = new Intent(this, DashboardActivity.class);
            startActivity(dashboardIntent);
        }
    }

    @Subscribe
    public void onUserRegister(RegisterEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "onRegister SUCCESS !!!");
            loadLoginFragment();
        }
    }

}
