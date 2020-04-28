package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uoodma.login_register.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mainDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button sendData, deleteRequest;
    private FirebaseAuth mAuth;

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        mAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        sendData=findViewById(R.id.sendData);
        setSupportActionBar(toolbar);

        Menu menu=navigationView.getMenu();
        menu.findItem(R.id.navLogout).setVisible(true);
        menu.findItem(R.id.navProfile).setVisible(true);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navDashboard);


        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);

        if (isFirstRun) {
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String email = prefs.getString("Email", " ");
            String password = prefs.getString("Password", " ");
            AuthCredential credential = EmailAuthProvider.getCredential(email, password);
            // Code to run once
            mAuth.getCurrentUser().linkWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = task.getResult().getUser();
                                Toast.makeText(mainDashboard.this, "Merged", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mainDashboard.this, "Failed to merge" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
          switch (menuItem.getItemId()){
              case R.id.navDashboard:
                  break;
              case R.id.navEditProfile:
                  Intent intent=new Intent(mainDashboard.this,editProfile.class);
                  startActivity(intent);
                  break;
              case R.id.navUploadDoc:
                  Intent intent1=new Intent(mainDashboard.this,uploadDocuments.class);
                  startActivity(intent1);
                  break;
              case R.id.navLogout:
                  mAuth.signOut();
                  Intent intentLogout = new Intent(mainDashboard.this, MainActivity.class);
                  startActivity(intentLogout);
                  finish();

          }
          drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

    public void onSendRequest(View view) {
        Intent intent2=new Intent(mainDashboard.this,sendData.class);
        startActivity(intent2);
    }


}