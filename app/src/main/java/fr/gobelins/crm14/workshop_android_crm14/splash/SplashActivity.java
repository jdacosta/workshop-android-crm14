package fr.gobelins.crm14.workshop_android_crm14.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreen";
    private static final int SLEEP_TIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        loadSplashScreen();
    }

    private void loadSplashScreen() {
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private void loadHomeActivity() {
        Intent homeActivityIntent = new Intent(this, HomeActivity.class);
        startActivity(homeActivityIntent);
    }

    private class IntentLauncher extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            loadHomeActivity();
        }
    }
}
