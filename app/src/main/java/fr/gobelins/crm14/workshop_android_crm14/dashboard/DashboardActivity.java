package fr.gobelins.crm14.workshop_android_crm14.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.home.HomeActivity;
import fr.gobelins.crm14.workshop_android_crm14.services.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User currentUser = AuthService.getInstance().getCurrentUser();

        if (currentUser == null) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        } else {
            setContentView(R.layout.activity_dashboard);
        }
    }
}
