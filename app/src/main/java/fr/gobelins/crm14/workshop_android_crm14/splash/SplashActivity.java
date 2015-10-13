package fr.gobelins.crm14.workshop_android_crm14.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadHomeActivity();
    }

    private void loadHomeActivity() {
        Intent homeActivityIntent = new Intent(this, HomeActivity.class);
        startActivity(homeActivityIntent);
    }
}
