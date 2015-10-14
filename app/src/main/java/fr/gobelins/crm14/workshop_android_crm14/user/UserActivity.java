package fr.gobelins.crm14.workshop_android_crm14.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}
