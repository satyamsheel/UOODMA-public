package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class editProfile extends AppCompatActivity {

    EditText registeredEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        registeredEmailId = findViewById(R.id.registeredEmailId);
        registeredEmailId.setText("Hello");

    }
}
