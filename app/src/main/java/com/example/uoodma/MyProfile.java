package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyProfile extends AppCompatActivity {

    TextView myProfileName;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ImageView myProfileQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myProfileName = findViewById(R.id.myProfileName);
        firebaseUser = firebaseAuth.getCurrentUser();
        myProfileQRCode = findViewById(R.id.myProfileQRCode);

        myProfileName.setText(firebaseUser.getDisplayName());

    }
}
