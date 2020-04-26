package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class mainDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
Button sendData,deleteRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        sendData=findViewById(R.id.sendData);
        deleteRequest=findViewById(R.id.deleteRequest);
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
          }
          drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

    public void onSendRequest(View view) {
        Intent intent2=new Intent(mainDashboard.this,sendData.class);
        startActivity(intent2);
    }

}