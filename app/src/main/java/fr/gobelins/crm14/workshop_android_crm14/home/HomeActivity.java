package fr.gobelins.crm14.workshop_android_crm14.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.dashboard.DashboardActivity;
import fr.gobelins.crm14.workshop_android_crm14.home.fragments.LoginFragment;
import fr.gobelins.crm14.workshop_android_crm14.home.fragments.RegisterFragment;

public class HomeActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener{

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // instantiate toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);

        // instantiate login fragment
        loginFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHomeLoginItem) {
            loginFragment();
            return true;
        }
        else if (item.getItemId() == R.id.menuHomeRegisterItem) {
            registerFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    private void loginFragment() {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.homeContainer, new LoginFragment())
            .commit();

        getSupportActionBar().setTitle(R.string.R_string_home_toolbar_login_title);
    }

    private void registerFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.homeContainer, new RegisterFragment())
                .commit();

        getSupportActionBar().setTitle(R.string.R_string_home_toolbar_register_title);
    }

    @Override
    public void onLogin() {
        Log.d(TAG, "onLogin");
        Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        startActivity(dashboardIntent);
    }
}
