package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uoodma.login_register.phoneVerificationActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    EditText editProfileEmailText, editProfileFullNameText, editProfilePhoneNumberText, editProfileAlternatePhoneNumberText,
            fullAddressText, editProfileCityText, editProfileStateText, editProfilePinCodeText;
    Button editProfileSaveChanges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfileEmailText = findViewById(R.id.editProfileEmailText);
        editProfileFullNameText = findViewById(R.id.editProfileFullNameText);
        editProfilePhoneNumberText = findViewById(R.id.editProfilePhoneNumberText);
        editProfileAlternatePhoneNumberText = findViewById(R.id.editProfileAlternatePhoneNumberText);
        fullAddressText = findViewById(R.id.fullAddressText);
        editProfileCityText = findViewById(R.id.editProfileCityText);
        editProfileStateText = findViewById(R.id.editProfileStateText);
        editProfilePinCodeText = findViewById(R.id.editProfilePinCodeText);
        editProfileSaveChanges = findViewById(R.id.editProfileSaveChanges);


        editProfileSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("Users").document(userId);
                Map<String, Object> user = new HashMap<>();
                user.put("Alternate Phone", editProfileAlternatePhoneNumberText.getText().toString());
                user.put("Full Address", fullAddressText.getText().toString());
                user.put("City Name", editProfileCityText.getText().toString());
                user.put("State Name", editProfileStateText.getText().toString());
                user.put("Pin Code", editProfilePinCodeText.getText().toString());
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(editProfile.this, "Sucess", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}



