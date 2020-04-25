package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class mainDashboard extends AppCompatActivity {
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        userName = findViewById(R.id.userName);
        Intent intent = getIntent();
        String username = intent.getStringExtra("First Name");
        userName.setText(username);
    }
}
